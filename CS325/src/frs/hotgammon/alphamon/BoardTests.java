package frs.hotgammon.alphamon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardTests {
	
	Game game;
	
	@Before
	public void setUp() {
		game = new GameImpl();
	}

	@Test
	public void ShouldBe2BlackOnR1(){
		
		BoardPoint newLoc = new BoardPoint(Location.R1, Color.BLACK, 2);
		
		
		assertEquals(" Color is black ", Color.BLACK, newLoc.colorOfLocation);
		assertEquals(" Location is R1", Location.R1, newLoc.locationName);
		assertEquals(" 2 Checkers", 2, newLoc.numberOfCheckers);
		
	}
	
	@Test
	public void shouldNotBeAbleToPlaceTwoDifferentColorsOnSamePoint(){
		
		game.newGame();
		
		assertEquals(" cant have two different colors in one point", false, game.move(Location.R12, Location.R8));
		
	}
	
	@Test
	public void shouldBeAbleToPlaceSameColorsOnSamePoint(){
		
		game.newGame();
		
		assertEquals(" valid move between two colors", true, game.move(Location.R12, Location.R1));
		
	}
	
	@Test
	public void shouldNotBeAbleToRemovePlayerOfWrongColor(){
		
		game.newGame();
		
		assertEquals(" cant move a checker of wrong color for player", false, game.move(Location.R6, Location.R8));
		
	}
	
	@Test
	public void shouldBeAbleToRemovePlayerOfCorrectColor(){
		
		game.newGame();
		
		assertEquals("can move a checker of the correct color", true, game.move(Location.R12, Location.R1));
		
	}
	
	@Test
	public void shouldChangeColor(){
		
		game.newGame();
		
		assertEquals("should change colors ", Color.NONE, game.getColor(Location.R2));
		
	}
	
	@Test
	public void shouldMove1fromR1toR2(){
		
		game.newGame();
			
		assertEquals("can move a checker of the correct color", true, game.move(Location.R1, Location.R2));
		
		game.makeMove(Location.R1, Location.R2);
		
		assertEquals("can move a checker of the correct color R1", 1, game.getCount(Location.R1));
		assertEquals("can move a checker of the correct color R2", 1, game.getCount(Location.R2));
		
	}
	
	
	@Test
	public void shouldNotMove1fromR1toB1(){
		
		game.newGame();
		
		assertEquals("can move a checker of the correct color", false, game.move(Location.R1, Location.B1));
		
		game.makeMove(Location.R1, Location.B1);
		
		assertEquals("can move a checker of the correct color R1", 2, game.getCount(Location.R1));
		assertEquals("can move a checker of the correct color R2", 2, game.getCount(Location.B1));
		
	}
	
	@Test
	public void shouldNotMoveOutOfTurn(){
		
		game.newGame();
			
		assertEquals("can move a checker of the correct color", false, game.move(Location.R8, Location.R7
				));
		
		
	}

}
