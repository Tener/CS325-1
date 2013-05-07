package frs.hotgammon.common;



import frs.hotgammon.Board;
import frs.hotgammon.BoardPoint;
import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Location;



public class BoardImpl implements Board{
	
	
	public final static Location[] BLACK_TABLE = {Location.B1, Location.B2, Location.B3, Location.B4, Location.B5, Location.B6};
	
	public final static Location[] RED_TABLE = {Location.R1, Location.R2, Location.R3, Location.R4, Location.R5, Location.R6};
	
	
	public final static Location[] BLACK_OUT_TABLE = {Location.R1, Location.R2, Location.R3, Location.R4, Location.R5, Location.R6, 
			Location.R7, Location.R8, Location.R9, Location.R10, Location.R11, Location.R12, 
			Location.B12, Location.B11, Location.B10, Location.B9, Location.B8, Location.B7};
			
	public final static Location[] RED_OUT_TABLE = {Location.R7, Location.R8, Location.R9, Location.R10, Location.R11, Location.R12, 
			Location.B12, Location.B11, Location.B10, Location.B9, Location.B8, Location.B7,
			Location.B6, Location.B5, Location.B4, Location.B3, Location.B2, Location.B1};


	private final int SQUARES_ON_BOARD = 28;
	public BoardPoint[] board = new BoardPoint[SQUARES_ON_BOARD];

	public BoardImpl(){ 
		for(int i = 0; i < SQUARES_ON_BOARD; i++){
			board[i] = new BoardPoint();
		}
	}

	
	public boolean put(Color color, int point) {
		BoardPoint bPoint = board[point];
		
		if (bPoint.playerInTurn != Color.NONE && bPoint.playerInTurn != color) {
			return false;
		}
		
		bPoint.checkers = bPoint.checkers + 1;
		
		bPoint.playerInTurn = color;
		return true;
	}


	public BoardPoint returnPoint(int num) {
		return board[num];
	}
	
	
	public boolean move(Location from, Location to, Color playerInTurn){

		boolean addBoolean = place(playerInTurn,to.ordinal());
		boolean removeBoolean = remove(playerInTurn, from.ordinal());
		
		if((addBoolean == true) && (removeBoolean == true)){
			return true;
		}
		else{
			return false;
		}

	}
	
	
	public boolean place(Color col, int number){

		if( number > -1 && number < SQUARES_ON_BOARD){
			
				board[number].add(col);
				return true;
		}
		return false;
	}

	
	public boolean remove(Color col, int number){

		if( number > -1 && number < SQUARES_ON_BOARD){
			
			if( board[number].playerInTurn == col ){
				
				board[number].remove(col);
				return true;
			}
		}
		return false;
	}
	
	public Location[] getBlackOutTable(){
		return BoardImpl.BLACK_OUT_TABLE;

	}
	
	public Location[] getRedOutTable(){
		return BoardImpl.RED_OUT_TABLE;

	}
	
	public Location[] getBlackTable(){
		return BoardImpl.BLACK_TABLE;

	}
	
	public Location[] getRedTable(){
		return BoardImpl.RED_TABLE;

	}
	
	public BoardPoint[] getBoard(){
		return board;
	}
	

}
