package frs.hotgammon.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import frs.hotgammon.Board;
import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.GameObserver;
import frs.hotgammon.HotgammonFactory;
import frs.hotgammon.framework.Location;
import frs.hotgammon.BoardPoint;
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
	public int movesPlayerHas;
	public int numberOfTurns;
	public int[] currentDice;
	public List<Integer> diceRolled;
	
	public Game game;
	
	private HotgammonFactory factory;
	public ArrayList<GameObserver> observers = new ArrayList<GameObserver>();
	
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
		
		colorInTurn = Color.NONE;
		currentDice = null;

		numberOfTurns = 0;
		
		configure(new Placement[] {
	    		
	    		//Place blacks
	    		new Placement(Color.BLACK,Location.B6),
	    		new Placement(Color.BLACK,Location.B6),
	    		new Placement(Color.BLACK,Location.B6),
	    		new Placement(Color.BLACK,Location.B6),
	    		new Placement(Color.BLACK,Location.B6),
	    		new Placement(Color.BLACK,Location.B8),
//	    		new Placement(Color.BLACK,Location.B8),
//	    		new Placement(Color.BLACK,Location.B8),
//	    		new Placement(Color.BLACK,Location.R12),
//	    		new Placement(Color.BLACK,Location.R12),
//	    		new Placement(Color.BLACK,Location.R12),
//	    		new Placement(Color.BLACK,Location.R12),
//	    		new Placement(Color.BLACK,Location.R12),
//	    		new Placement(Color.BLACK,Location.R1),
//	    		new Placement(Color.BLACK,Location.R1),
	    		
	    		//Place reds
//	    		new Placement(Color.RED,Location.B1),
//	    		new Placement(Color.RED,Location.B1),
//	    		new Placement(Color.RED,Location.B12),
//	    		new Placement(Color.RED,Location.B12),
//	    		new Placement(Color.RED,Location.B12),
//	    		new Placement(Color.RED,Location.B12),
//	    		new Placement(Color.RED,Location.B12),
//	    		new Placement(Color.RED,Location.R8),
//	    		new Placement(Color.RED,Location.R8),
	    		new Placement(Color.RED,Location.R8),		
	    		new Placement(Color.RED,Location.R6),
	    		new Placement(Color.RED,Location.R6),
	    		new Placement(Color.RED,Location.R6),
	    		new Placement(Color.RED,Location.R6),
	    		new Placement(Color.RED,Location.R6)		
	    		
	    });

	}

	public void nextTurn() {

		
		colorInTurn = factory.getTurnDeterminer().nextTurnChangePlayer(colorInTurn, this);
		
		
		currentDice = diceThrown();
		
		if( numberOfTurns == 0){
			
			colorInTurn = findPlayerToStartGame(currentDice);
			
			for( GameObserver gameObserver : this.observers ){
				  gameObserver.diceRolled(currentDice);
				  gameObserver.setStatus("Determining the starting player by the opeining die roll. If you do not have a valid move, double click on the die to roll next players turn.");
			  }
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			currentDice = diceThrown();
			
		}
		
		if (currentDice.length == 2){
			diceRolled = new ArrayList<Integer>(Arrays.asList(currentDice[0], currentDice[1]));
		}
		else{
			diceRolled = new ArrayList<Integer>(Arrays.asList(currentDice[0], currentDice[1], currentDice[2], currentDice[3]));
		}
		
		numberOfTurns++;
		
		updateNumberOfMoves();
		
		for( GameObserver gameObserver : this.observers ){
			  gameObserver.diceRolled(currentDice);
			  gameObserver.setStatus("Player " + getPlayerInTurn().toString() + " has "+ getNumberOfMovesLeft() +" moves. Die: " + this.diceRolled + " If no valid move, click on die.");
		  }
	}
	
	
	
	public Color findPlayerToStartGame (int[] currentDice){
		
		System.out.println("Find Starting Player");

		
		return (currentDice[0] > currentDice[1]) ? Color.RED : Color.BLACK;
	}
	
	public void updateNumberOfMoves(){
		
		int newNumberOfMoves = diceRolled.size();
		movesPlayerHas = newNumberOfMoves;
	}

	
	
	public boolean move(Location from, Location to) {
		
		if(winner() != Color.NONE){
			for( GameObserver gameObserver : this.observers ){
				  
				  gameObserver.setStatus(winner() + " Won the game");
			  }
			
			return false;
		}
		
		System.out.println( from + " " + to + " " + getColor(Location.R_BEAR_OFF) + getCount(Location.R_BEAR_OFF));
		System.out.println( getColor(Location.B_BEAR_OFF) +" " + getCount(Location.B_BEAR_OFF));
		
		if(from == Location.R_BEAR_OFF || from == Location.B_BEAR_OFF){
			
			if(numberOfTurns == 0){
			
			  Color colorOfChecker = (from == Location.R_BEAR_OFF) ? Color.RED : Color.BLACK;
			  
			  //Move Checker to board
			  board.move(from, to, colorOfChecker);
			  
			  for( GameObserver gameObserver : this.observers ){
				  gameObserver.checkerMove(from, to);
			  }
			  return true;
			}
		  }
		
	  
	  if( movesPlayerHas == 0){
		  
		  for( GameObserver gameObserver : this.observers ){
			  gameObserver.checkerMove(from, from);
			  gameObserver.setStatus("Invalid move you have 0 moves left");
		  }
		  
		  return false;
	  }
	  
	  
	  
	  if (isAllPicesInInnerTable()){
		  if(!checkIfIsValidMoveInsideTable()){
			  movesPlayerHas = 0;
		  for( GameObserver gameObserver : this.observers ){
			  gameObserver.checkerMove(from, from);
			  gameObserver.setStatus("There is no valid move left to make. Your turn has expired. Opposite player please roll dice");
		  }
		  }
	  }
	  
	  
	  if(!isAnyValidMovesLeftFromBar()){
		  movesPlayerHas = 0;
		  
		  for( GameObserver gameObserver : this.observers ){
			  gameObserver.checkerMove(from, from);
			  gameObserver.setStatus("There are no valid moves left. Passing turn and moving on to next player");
		  }
		  
	  }
	  
	  if( factory.getMoveValidator().isValid(from, to)){
		  
		  System.out.println("Dice Rolled in move of game " + diceRolled.toString());
		  
		  if(getCount(to) == 1 && getColor(to) != colorInTurn){
			  opponentToBar(to);
		  }
		  
		  
		  
		  if (testAndDoCanBeMoved(from, to)){
			  
			  movesPlayerHas--;
			  
			  int dieUsed = (colorInTurn == Color.BLACK) ? Location.distance(from, to) : (-1 * Location.distance(from, to));
			  
			  int indexInDiceRolled = diceRolled.indexOf(dieUsed);
			  
			  diceRolled.remove(indexInDiceRolled);
			  
			  for( GameObserver gameObserver : this.observers ){
				  gameObserver.checkerMove(from, to);
				  gameObserver.setStatus("Valid Move " + getPlayerInTurn().toString() + " has "+ getNumberOfMovesLeft() +" moves left. Die: " + this.diceRolled + " If no valid move, click on die.");
			  }
			  
			  return true;
		  }
		  
		  else{
		
			  for( GameObserver gameObserver : this.observers ){
				  gameObserver.checkerMove(from, from);
				  gameObserver.setStatus("Invalid Move: " + getPlayerInTurn().toString() + " has "+ getNumberOfMovesLeft() +" moves left. Die: " + this.diceRolled + " If no valid move, click on die.");
			  }
		  }
		  
	  }
	  return false;
				 
		
  }
	
	public boolean isAnyValidMovesLeftFromBar(){
		
		Location currentBar = ((colorInTurn == Color.BLACK)? Location.B_BAR : Location.R_BAR);
		
		if (getCount(currentBar) > 0 ){
			for( int i = 0; i < diceRolled.size(); i++){
				if(factory.getMoveValidator().isValid(currentBar, (Location.findLocation(colorInTurn, currentBar, diceRolled.get(i))))){
					return true;
				}
			}
			
			return false;
		}
		return true;
	}
	
	public boolean isAllPicesInInnerTable(){		
		Location [] outsideTable = (colorInTurn == Color.BLACK) ? board.getBlackOutTable() : board.getRedOutTable();
			for(int i = 0; i < outsideTable.length; i++){
				if( getCount(outsideTable[i]) > 0){
					
					if(getColor(outsideTable[i]) == colorInTurn){
						
						
						return false;
					}
				}
			}
		return true;
	}
	
	public boolean checkIfIsValidMoveOutsideTable(){
	
		Location [] outsideTable = (colorInTurn == Color.BLACK) ? board.getBlackOutTable() : board.getRedOutTable();
		
		for(int i = 0; i < outsideTable.length; i++){
			if(getCount(outsideTable[i]) > 0){
		
				if(getColor(outsideTable[i]) == colorInTurn){
			
					if(factory.getMoveValidator().isValid(outsideTable[i], (Location.findLocation(colorInTurn, outsideTable[i], diceRolled.get(i))))){
						return true;
					}

				}
			}
		}
		
		return false;
	}
	
	public boolean checkIfIsValidMoveInsideTable(){
		
		Location [] insideTable = (colorInTurn == Color.BLACK) ? board.getBlackTable() : board.getRedTable();
		
		for(int i = 0; i < insideTable.length; i++){
			if( getCount(insideTable[i]) > 0){
		
				if(getColor(insideTable[i]) == colorInTurn){
					
					for( int j = 0; j < diceRolled.size(); j++){
			
					if(factory.getMoveValidator().isValid(insideTable[i], (Location.findLocation(colorInTurn, insideTable[i], diceRolled.get(j))))){
						
						for( GameObserver gameObserver : this.observers ){
							  
							  gameObserver.setStatus("There is no valid move left to make. Your turn has expired. Opposite player please roll dice");
						}
						return true;
					}
					}

				}
			}
		}
		
		return false;
	}
	
	
	public boolean testAndDoCanBeMoved(Location from, Location to){
		
		boolean canBeRemoved = board.remove(colorInTurn, from.ordinal());
		
		boolean canBeAdded = board.place(colorInTurn,to.ordinal());
		
		if(canBeRemoved == true && canBeAdded == true){
			return true;
		}
		return false;
	}

	public Color getPlayerInTurn() {
		return colorInTurn;
	}

	public int getNumberOfMovesLeft() {

		return movesPlayerHas;

	}

	public int[] diceThrown() {
		
		
		int[] newDiceArray = factory.getRollDeterminer().diceThrown(this);
		

		return newDiceArray;

	}

	public int[] diceValuesLeft() {
		
		int[] diceValuesLeft = new int[diceRolled.size()];
		
		
		  for (int i = 0; i < diceRolled.size(); i++){
			  
			  diceValuesLeft[i] = diceRolled.get(i);
		  }
		  return diceValuesLeft;
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
	    	//From location
	    	Location from = getPlayerBearOff(placements[i].player);
	    	
	    	//Place Checker on board
	    	board.place(placements[i].player, from.ordinal());
	        move(from, placements[i].location);	        
	    }
	}
	
	 
	 private void opponentToBar (Location loc){
		 //Get opponentColor
		 Color opponentColor = opponentColor();
		 
		 Location otherPlayerBar = (opponentColor == Color.BLACK) ? Location.B_BAR : Location.R_BAR;
		  
		 System.out.println("Moving Checker To The Bar");
		 board.place(opponentColor, otherPlayerBar.ordinal());
		  
		 board.remove(opponentColor, loc.ordinal());

		  for( GameObserver gameObserver : this.observers ){
			  gameObserver.checkerMove(loc, otherPlayerBar);
		  }
		 
	 }
	 
	 private Color opponentColor(){
		  if(colorInTurn == Color.NONE){
			  return Color.NONE;
		  }
		  return (colorInTurn == Color.RED) ? Color.BLACK : Color.RED;
	  }
	 
	 private Location getPlayerBearOff(Color player){
			return ( player == Color.BLACK) ? Location.B_BEAR_OFF : Location.R_BEAR_OFF;
		}

	 public void addObserver(GameObserver gl) {
		  this.observers.add(gl);
	  }
	  
	  public ArrayList<GameObserver> getObservers(){
			return this.observers;
		}
}
