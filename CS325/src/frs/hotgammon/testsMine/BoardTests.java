package frs.hotgammon.testsMine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.Location;
import frs.hotgammon.common.BoardImpl;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.variants.movevalidators.SimpleMoveValidator;

public class BoardTests {
	
	Game game;
	
	public BoardImpl board;
	
	@Before
	public void setup(){
		board = new BoardImpl(25);
	}

	@Test
	public void shouldNotBeAbleToPlaceTwoDifferentColorsOnSameSquare() {
		assertTrue(board.put(Color.BLACK, Location.R1.ordinal()));
		assertFalse(board.put(Color.RED, Location.R1.ordinal()));
	}

	@Test
	public void shouldBeAbleToPlaceSameColorOnGivenSquare() {
		assertTrue(board.put(Color.BLACK, Location.R1.ordinal()));
		assertTrue(board.put(Color.BLACK, Location.R1.ordinal()));
	}

	@Test
	public void shouldReturnProperCountForGivenSquare() {
		board.put(Color.BLACK, Location.R1.ordinal());
		board.put(Color.BLACK, Location.R1.ordinal());
		assertEquals(2, board.returnPoint(Location.R1.ordinal()).checkers);
	}

	@Test
	public void shouldNotBeAbleToRemovePlayerOfWrongColor() {
		board.put(Color.BLACK, Location.R1.ordinal());
		
		assertEquals(1, board.returnPoint(Location.R1.ordinal()).checkers);
	}

}
