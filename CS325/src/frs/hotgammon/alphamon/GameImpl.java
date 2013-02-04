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
	public HashMap <Location,BoardPoint> board = null;
	  
  public void newGame() {
	  
	  board = new HashMap<Location, BoardPoint>();
		for (Location l : Location.values()) {
			board.put(l, new BoardPoint());
		}
		board.get(Location.B1).setNumberOfCheckers(2);
		board.get(Location.B1).setColorOfLocation(Color.RED);
		
		board.get(Location.B6).setNumberOfCheckers(5);
		board.get(Location.B6).setColorOfLocation(Color.BLACK);
		
		board.get(Location.B8).setNumberOfCheckers(3);
		board.get(Location.B8).setColorOfLocation(Color.BLACK);
		
		board.get(Location.B12).setNumberOfCheckers(5);
		board.get(Location.B12).setColorOfLocation(Color.RED);
		
		board.get(Location.R1).setNumberOfCheckers(2);
		board.get(Location.R1).setColorOfLocation(Color.BLACK);
		
		board.get(Location.R6).setNumberOfCheckers(5);
		board.get(Location.R6).setColorOfLocation(Color.RED);
		
		board.get(Location.R8).setNumberOfCheckers(3);
		board.get(Location.R8).setColorOfLocation(Color.RED);
		
		board.get(Location.R12).setNumberOfCheckers(5);
		board.get(Location.R12).setColorOfLocation(Color.BLACK);
		
		this.nextTurn();

  }
  public void nextTurn() {
	  
	  this.colorInTurn = Color.BLACK;
  }
  public boolean move(Location from, Location to) { 
	  if( colorInTurn == board.get(from).colorOfLocation){
		  if( board.get(from).colorOfLocation == board.get(to).colorOfLocation || board.get(from).colorOfLocation == Color.NONE){
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
		 int tempFrom  = board.get(from).getNumberOfCheckers();
		 int tempTo = board.get(to).getNumberOfCheckers();
		 
		 if(board.get(to).colorOfLocation == Color.NONE){
			 board.get(to).setColorOfLocation (board.get(from).colorOfLocation);
		 }
		 
		 board.get(from).setNumberOfCheckers(tempFrom - 1);
		 board.get(to).setNumberOfCheckers(tempTo + 1);
	  }
	  
	  else{
		  System.out.println("Can not make this move");
		  return;
	  }
		 
  }
  
  public Color getPlayerInTurn() { 
	  return colorInTurn; 
	  }
  
  
  public int getNumberOfMovesLeft() { return 0; }
  public int[] diceThrown() { return new int[] {1,1}; }
  public int[] diceValuesLeft() { return new int []{}; }
  public Color winner() { return Color.NONE; }
  public Color getColor(Location location) { return Color.NONE; }
  public int getCount(Location location) { return 0; }
}
