package frs.hotgammon.common;



import frs.hotgammon.Board;
import frs.hotgammon.BoardPoint;
import frs.hotgammon.Color;
import frs.hotgammon.Location;



public class BoardImpl implements Board{
	
	public BoardPoint[] board;
	public final static Location[] BLACK_TABLE = {Location.B1, Location.B2, Location.B3, Location.B4, Location.B5, Location.B6};
	public final static Location[] RED_TABLE = {Location.R1, Location.R2, Location.R3, Location.R4, Location.R5, Location.R6};



	public BoardImpl(int points) {
		
		board = new BoardPoint[points];
		for (int i = 0; i < points; i++) {
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
	
	

}
