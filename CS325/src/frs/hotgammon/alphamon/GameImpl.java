package frs.hotgammon.alphamon;

import java.util.HashMap;

/** Skeleton implementation of HotGammon.
 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
*/

public class GameImpl implements Game {
	
	public Color colorInTurn = Color.NONE;
	public BoardPoint[] board = null;
	public int numberOfTurns;
	public int [] currentDice;
	public int currentDiceIndex;
	  
  public void newGame() {
	  
	  numberOfTurns = 0;
	  
	  board = new BoardPoint[Location.N_LOCATIONS];
	  
	  for(int i = 0; i< Location.N_LOCATIONS; i++){
		  
		  board[i] = new BoardPoint();
	  }
	  
	  board[Location.B1.ordinal()].setNumberOfCheckers(2);
	  board[Location.B1.ordinal()].setColorOfLocation(Color.RED);
	  
	  board[Location.B6.ordinal()].setNumberOfCheckers(5);
	  board[Location.B6.ordinal()].setColorOfLocation(Color.BLACK);
	  
	  board[Location.B8.ordinal()].setNumberOfCheckers(3);
	  board[Location.B8.ordinal()].setColorOfLocation(Color.BLACK);
	  
	  board[Location.B12.ordinal()].setNumberOfCheckers(5);
	  board[Location.B12.ordinal()].setColorOfLocation(Color.RED);
	  
	  board[Location.R1.ordinal()].setNumberOfCheckers(2);
	  board[Location.R1.ordinal()].setColorOfLocation(Color.BLACK);
	  
	  board[Location.R6.ordinal()].setNumberOfCheckers(5);
	  board[Location.R6.ordinal()].setColorOfLocation(Color.RED);
	  
	  board[Location.R8.ordinal()].setNumberOfCheckers(3);
	  board[Location.R8.ordinal()].setColorOfLocation(Color.RED);
	  
	  board[Location.R12.ordinal()].setNumberOfCheckers(5);
	  board[Location.R12.ordinal()].setColorOfLocation(Color.BLACK);
		
	this.nextTurn();

  }
  public void nextTurn() {
	  
	  colorInTurn = (colorInTurn != Color.BLACK) ? Color.BLACK : Color.RED;
	    numberOfTurns++;
	    currentDice = diceThrown();
  }
  public boolean move(Location from, Location to) { 
	  
	  
	  if( colorInTurn == board[from.ordinal()].colorOfLocation){
		  if( board[from.ordinal()].colorOfLocation == board[to.ordinal()].colorOfLocation || board[to.ordinal()].colorOfLocation == Color.NONE){
			  return true;
		  }
		  else {
			  return false; 
		  }
	  }
	  else {
		  return false;
	  }
  }
  
  public void makeMove(Location from, Location to){
	  if(move(from, to) == true){
		 int tempFrom  = board[from.ordinal()].getNumberOfCheckers();
		 int tempTo = board[to.ordinal()].getNumberOfCheckers();
		 
		 if(board[to.ordinal()].colorOfLocation == Color.NONE){
			 board[to.ordinal()].setColorOfLocation (board[from.ordinal()].colorOfLocation);
		 }
		 
		 board[from.ordinal()].setNumberOfCheckers(tempFrom - 1);
		 board[to.ordinal()].setNumberOfCheckers(tempTo + 1);
		 
		 currentDiceIndex = currentDiceIndex - 1;
		 
		 System.out.println(board[from.ordinal()].getNumberOfCheckers());
		 System.out.println(board[to.ordinal()].getNumberOfCheckers());

	  }
	  
	  else{
		  System.out.println("Can not make this move");
		  return;
	  }
		 
  }
  
  public Color getPlayerInTurn() { 
	  return colorInTurn; 
	  }
  
  
  public int getNumberOfMovesLeft() { 
	  
	  return currentDiceIndex ; 
	  
  }
  public int[] diceThrown() { 
	  
	  currentDiceIndex = 2;
	  if(numberOfTurns % 3 == 1){
		  currentDice = new int[] {1,2};
	  }
	  
	  if(numberOfTurns % 3 == 2){
		  currentDice = new int[] {3,4};
	  }
	  
	  if(numberOfTurns % 3 == 0){
		  currentDice = new int[] {5,6};
	  }
	  
	  return currentDice;
	  
	  
  }
  
  public int[] diceValuesLeft() { 
	  
	  if (currentDice.length > 1 &&  currentDice[1] > currentDice[0])
		  return new int[] { currentDice[1], currentDice[0]};
	  
	  return currentDice; 
  }
  public Color winner() { 
	  
	  if(numberOfTurns < 6)
		  return Color.NONE;
	  else
		  return Color.RED;
  }
  
  public Color getColor(Location location) { 
	  
	  return board [location.ordinal()].getColorOfLocation(); 
	  
  }
  
  public int getCount(Location location) { 
	  
	  return board[location.ordinal()].getNumberOfCheckers(); 
	 
  }
}
