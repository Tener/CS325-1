package frs.hotgammon.common;

import java.util.HashMap;

import frs.hotgammon.Board;
import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.GameObserver;
import frs.hotgammon.HotgammonFactory;
import frs.hotgammon.framework.Location;
import frs.hotgammon.MoveValidator;
import frs.hotgammon.RollDeterminer;
import frs.hotgammon.common.GameImpl.Placement;
import frs.hotgammon.TurnDeterminer;
import frs.hotgammon.WinnerDeterminer;


/**
 * Skeleton implementation of HotGammon.
 * 
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 * 
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */

public class GameImpl implements Game {

	public Color colorInTurn = Color.NONE;
	public Board board;
	public int numberOfTurns;
	public int[] currentDice;
	public int currentDiceIndex;

	public Game game;
	
	private HotgammonFactory factory;
	
	public GameImpl(HotgammonFactory factory) {
		this.factory = factory;
		this.factory.setFactory(this);
	}

//	public GameImpl(MoveValidator validator, WinnerDeterminer winnerValidator, TurnDeterminer turnChangeValidator) {
//		this.validator = validator;
//		this.validator.setGame(this);
//		this.winnerValidator = winnerValidator;
//		this.winnerValidator.setGame(this);
//		this.turnChangeValidator = turnChangeValidator;
//		this.turnChangeValidator.setGame(this);
//	}

	public void newGame() {

		numberOfTurns = 0;

		board = new BoardImpl();
		
		configure(new Placement[] {
	    		new Placement(Color.RED,Location.B1),
	    		new Placement(Color.RED,Location.B1),
	    		new Placement(Color.BLACK,Location.B6),
	    		new Placement(Color.BLACK,Location.B6),
	    		new Placement(Color.BLACK,Location.B6),
	    		new Placement(Color.BLACK,Location.B6),
	    		new Placement(Color.BLACK,Location.B6),
	    		new Placement(Color.BLACK,Location.B8),
	    		new Placement(Color.BLACK,Location.B8),
	    		new Placement(Color.BLACK,Location.B8),
	    		new Placement(Color.RED,Location.B12),
	    		new Placement(Color.RED,Location.B12),
	    		new Placement(Color.RED,Location.B12),
	    		new Placement(Color.RED,Location.B12),
	    		new Placement(Color.RED,Location.B12),
	    		new Placement(Color.BLACK,Location.R12),
	    		new Placement(Color.BLACK,Location.R12),
	    		new Placement(Color.BLACK,Location.R12),
	    		new Placement(Color.BLACK,Location.R12),
	    		new Placement(Color.BLACK,Location.R12),		
	    		new Placement(Color.RED,Location.R8),
	    		new Placement(Color.RED,Location.R8),
	    		new Placement(Color.RED,Location.R8),		
	    		new Placement(Color.RED,Location.R6),
	    		new Placement(Color.RED,Location.R6),
	    		new Placement(Color.RED,Location.R6),
	    		new Placement(Color.RED,Location.R6),
	    		new Placement(Color.RED,Location.R6),		
	    		new Placement(Color.BLACK,Location.R1),
	    		new Placement(Color.BLACK,Location.R1),
	    });


		colorInTurn = Color.NONE;
		currentDice = null;
	}

	public void nextTurn() {

		colorInTurn = factory.getTurnDeterminer().nextTurnChangePlayer(colorInTurn, this);
		factory.getWinnerDeterminer().makeATurn();
		numberOfTurns++;
		currentDice = diceThrown();
		this.factory.setFactory(this);
	}

	public boolean move(Location from, Location to) { 
	  
	  if( currentDiceIndex == 0){
		  return false;
	  }
	  
	  if( factory.getMoveValidator().isValid(from, to)){
		  
		  if (getCount(to) > 0 && getColor(to) != getPlayerInTurn()) {
			  Color playerColor = this.getColor(from);
			  Color playerBarColor = this.getColor(to);
				Location bar = playerBarColor == Color.RED ? Location.R_BAR : Location.B_BAR;
				
				int tempFrom  = board.returnPoint(from.ordinal()).checkers;
				  int tempBar = board.returnPoint(bar.ordinal()).checkers;
						 
				  board.returnPoint(from.ordinal()).checkers = tempFrom - 1;
				  board.returnPoint(to.ordinal()).playerInTurn = playerColor;
				  board.returnPoint(bar.ordinal()).checkers = tempBar + 1;
						 
				  if(board.returnPoint(from.ordinal()).checkers == 0){
					  board.returnPoint(from.ordinal()).playerInTurn = Color.NONE;
				  }
						 
				  currentDiceIndex = currentDiceIndex - 1;
				  
				  return true;
			}
		  
		  int tempFrom  = board.returnPoint(from.ordinal()).checkers;
		  int tempTo = board.returnPoint(to.ordinal()).checkers;
				 
		  if(board.returnPoint(to.ordinal()).playerInTurn == Color.NONE){
			  board.returnPoint(to.ordinal()).playerInTurn = board.returnPoint(from.ordinal()).playerInTurn;
		  }
				 
		  board.returnPoint(from.ordinal()).checkers = tempFrom - 1;
		  board.returnPoint(to.ordinal()).checkers = tempTo + 1;
				 
		  if(board.returnPoint(from.ordinal()).checkers == 0){
			  board.returnPoint(from.ordinal()).playerInTurn = Color.NONE;
		  }
				 
		  currentDiceIndex = currentDiceIndex - 1;
		  
		  return true;
	  }
	  return false;
				 
		
  }

	public Color getPlayerInTurn() {
		return colorInTurn;
	}

	public int getNumberOfMovesLeft() {

		return currentDiceIndex;

	}

	public int[] diceThrown() {

		currentDiceIndex = 2;
		
		currentDice = factory.getRollDeterminer().diceThrown(this);

		return currentDice;

	}

	public int[] diceValuesLeft() {

		if (currentDice.length > 1 && currentDice[1] > currentDice[0])
			return new int[] { currentDice[1], currentDice[0] };

		return currentDice;
	}

	public Color winner() {

		return factory.getWinnerDeterminer().returnWinner();
	}

	public Color getColor(Location location) {

		
		return board.returnPoint(location.ordinal()).playerInTurn;

	}

	public int getCount(Location location) {
		return board.returnPoint(location.ordinal()).checkers;

	}


	public Board playingBoard() {
		// TODO Auto-generated method stub
		return board;
	}
	
	
	static public class Placement {
	    public Location location;
	    public Color    player;
	    public Placement(Color player, Location location) {
	        this.player = player;
	        this.location = location;
	    }
	}
  	
	public void configure(Placement[] placements) {
		board = new BoardImpl();
	    for (int i = 0; i < placements.length; i++) {
	        //gameBoard.place(placements[i].player, placements[i].location.ordinal());	
	    	Location from = getPlayerBearOff(placements[i].player);
	    	board.put(placements[i].player, from.ordinal());
	        move(from, placements[i].location);	        
	    }
	}
	
	 private Color determineStartingPlayer(int[] dRoll){
		  return (dRoll[0] > dRoll[1]) ? Color.RED : Color.BLACK;
	  }
	 
	 private Location getPlayerBearOff(Color player){
			return ( player == Color.BLACK) ? Location.B_BEAR_OFF : Location.R_BEAR_OFF;
		}

	@Override
	public void addObserver(GameObserver observer) {
		// TODO Auto-generated method stub
		
	}
}
