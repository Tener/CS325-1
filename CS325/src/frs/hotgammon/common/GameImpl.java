package frs.hotgammon.common;

import java.util.HashMap;

import frs.hotgammon.Board;
import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.Location;
import frs.hotgammon.MoveValidator;
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
	private MoveValidator validator;
	private WinnerDeterminer winnerValidator;
	private TurnDeterminer turnChangeValidator;

	public GameImpl(MoveValidator validator, WinnerDeterminer winnerValidator, TurnDeterminer turnChangeValidator) {
		this.validator = validator;
		this.validator.setGame(this);
		this.winnerValidator = winnerValidator;
		this.winnerValidator.setGame(this);
		this.turnChangeValidator = turnChangeValidator;
		this.turnChangeValidator.setGame(this);
	}

	public void newGame() {

		numberOfTurns = 0;

		board = new BoardImpl(28);
		
		board.put(Color.RED, Location.B1.ordinal());
		board.put(Color.RED, Location.B1.ordinal());
		
		board.put(Color.BLACK, Location.B6.ordinal());
		board.put(Color.BLACK, Location.B6.ordinal());
		board.put(Color.BLACK, Location.B6.ordinal());
		board.put(Color.BLACK, Location.B6.ordinal());
		board.put(Color.BLACK, Location.B6.ordinal());

		board.put(Color.BLACK, Location.B8.ordinal());
		board.put(Color.BLACK, Location.B8.ordinal());
		board.put(Color.BLACK, Location.B8.ordinal());
		
		board.put(Color.RED, Location.B12.ordinal());
		board.put(Color.RED, Location.B12.ordinal());
		board.put(Color.RED, Location.B12.ordinal());
		board.put(Color.RED, Location.B12.ordinal());
		board.put(Color.RED, Location.B12.ordinal());

		board.put(Color.BLACK, Location.R1.ordinal());
		board.put(Color.BLACK, Location.R1.ordinal());

		board.put(Color.RED, Location.R6.ordinal());
		board.put(Color.RED, Location.R6.ordinal());
		board.put(Color.RED, Location.R6.ordinal());
		board.put(Color.RED, Location.R6.ordinal());
		board.put(Color.RED, Location.R6.ordinal());

		board.put(Color.RED, Location.R8.ordinal());
		board.put(Color.RED, Location.R8.ordinal());
		board.put(Color.RED, Location.R8.ordinal());

		board.put(Color.BLACK, Location.R12.ordinal());
		board.put(Color.BLACK, Location.R12.ordinal());
		board.put(Color.BLACK, Location.R12.ordinal());
		board.put(Color.BLACK, Location.R12.ordinal());
		board.put(Color.BLACK, Location.R12.ordinal());


		colorInTurn = Color.NONE;
		currentDice = null;
	}

	public void nextTurn() {

		colorInTurn = turnChangeValidator.nextTurnChangePlayer(colorInTurn);
		winnerValidator.makeATurn();
		numberOfTurns++;
		currentDice = diceThrown();
	}

	public boolean move(Location from, Location to) { 
	  
	  if( currentDiceIndex == 0){
		  return false;
	  }
	  
	  if( validator.isValid(from, to)){
		  
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
		if (numberOfTurns % 3 == 1) {
			currentDice = new int[] { 1, 2 };
		}

		if (numberOfTurns % 3 == 2) {
			currentDice = new int[] { 3, 4 };
		}

		if (numberOfTurns % 3 == 0) {
			currentDice = new int[] { 5, 6 };
		}

		return currentDice;

	}

	public int[] diceValuesLeft() {

		if (currentDice.length > 1 && currentDice[1] > currentDice[0])
			return new int[] { currentDice[1], currentDice[0] };

		return currentDice;
	}

	public Color winner() {

		return winnerValidator.returnWinner();
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
		public Color	player;
		public Placement(Color player, Location location) {
			this.player = player;
			this.location = location;
		}
	}

	public void configure(Placement[] placements) {
		if (placements == null || placements.length == 0) {			
			return;
		}
		for (int i = 0; i < placements.length; i++) {
			board.put(placements[i].player, placements[i].location.ordinal());	
		}
	}
}
