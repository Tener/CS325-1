package frs.hotgammon.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.Location;
import frs.hotgammon.common.BoardImpl;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.variants.AlphaMoveValidator;

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
		assertEquals(2, board.getSquare(Location.R1.ordinal()).pieces);
	}

	@Test
	public void shouldNotBeAbleToRemovePlayerOfWrongColor() {
		board.put(Color.BLACK, Location.R1.ordinal());
		assertFalse(board.remove(Color.RED, Location.R1.ordinal()));
		assertEquals(1, board.getSquare(Location.R1.ordinal()).pieces);
	}

	@Test
	public void shouldBeAbleToRemovePlayerOfCorrectColor() {
		board.put(Color.BLACK, Location.R1.ordinal());
		assertTrue(board.remove(Color.BLACK, Location.R1.ordinal()));
		assertEquals(0, board.getSquare(Location.R1.ordinal()).pieces);
	}

}
