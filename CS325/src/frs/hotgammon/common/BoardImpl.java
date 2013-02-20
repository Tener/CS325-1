package frs.hotgammon.common;



import frs.hotgammon.Board;
import frs.hotgammon.BoardPoint;
import frs.hotgammon.Color;
import frs.hotgammon.Location;



public class BoardImpl implements Board{
	
	public BoardPoint[] board;
	public final Location BLACK_TABLE[]; 
			
			BLACKT_TABLE = new Location [6]{
	
	
	
	
	BLACK_TABLE [0] = Location.B1};
//	BLACK_TABLE [1] = Location.B2,
//	BLACK_TABLE [2] = Location.B3,
//	BLACK_TABLE [3] = Location.B4,
//	BLACK_TABLE [4] = Location.B5,
//	BLACK_TABLE [5] = Location.B6
//	};
	
//	public static final ArrayList<Location> blackInnerTable = new ArrayList<Location>() {
//		{
//
//			this.add(Location.B1);
//			this.add(Location.B2);
//			this.add(Location.B3);
//			this.add(Location.B4);
//			this.add(Location.B5);
//			this.add(Location.B6);
//		}
//	};
//
//	public static final ArrayList<Location> redInnerTable = new ArrayList<Location>() {
//		{
//
//			this.add(Location.R1);
//			this.add(Location.R2);
//			this.add(Location.R3);
//			this.add(Location.R4);
//			this.add(Location.R5);
//			this.add(Location.R6);
//		}
//	};

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
		bPoint.playerInTurn = color;
		bPoint.checkers = bPoint.checkers + 1;
		return true;
	}


	public BoardPoint returnPoint(int num) {
		return board[num];
	}
	
	

}
