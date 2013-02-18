package frs.hotgammon.common;

import java.util.ArrayList;

import frs.hotgammon.Board;
import frs.hotgammon.Color;
import frs.hotgammon.Location;
import frs.hotgammon.Square;


public class BoardImpl implements Board{
	
	public Square[] board;
	
	public static final ArrayList<Location> blackInnerTable = new ArrayList<Location>() {
		{

			this.add(Location.B1);
			this.add(Location.B2);
			this.add(Location.B3);
			this.add(Location.B4);
			this.add(Location.B5);
			this.add(Location.B6);
		}
	};

	public static final ArrayList<Location> redInnerTable = new ArrayList<Location>() {
		{

			this.add(Location.R1);
			this.add(Location.R2);
			this.add(Location.R3);
			this.add(Location.R4);
			this.add(Location.R5);
			this.add(Location.R6);
		}
	};

	public BoardImpl(int points) {
		
		board = new Square[points];
		for (int i = 0; i < points; i++) {
			board[i] = new Square();
		}
	}

	
	public boolean put(Color color, int point) {
		Square square = board[point];
		if (square.player != Color.NONE && square.player != color) {
			return false;
		}
		square.player = color;
		square.pieces = square.pieces + 1;
		return true;
	}

	public boolean remove(Color player, int sqNumber) {
		Square square = board[sqNumber];
		if (player != square.player) {
			return false;
		}
		square.pieces--;
		if (square.pieces == 0) {
			player = Color.NONE;;
		}
		return true;
	}

	public Square getSquare(int sqNumber) {
		return board[sqNumber];
	}
	
	

}
