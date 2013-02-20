package frs.hotgammon.testsMine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.Location;
import frs.hotgammon.common.GameImpl.Placement;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.variants.movevalidators.CompleteMoveValidator;
import frs.hotgammon.variants.turndeterminers.AlternatingTurnDeterminer;
import frs.hotgammon.variants.winnerdeterminers.SixMoveWinnerDeterminer;

public class BetaMonTests {

	GameImpl game;
	
	@Before
	public void setup() {
		game = new GameImpl(new CompleteMoveValidator(), new SixMoveWinnerDeterminer(), new AlternatingTurnDeterminer());
		game.newGame();
	}
	
	@Test
	public void illegalBlackMove() {
		game.nextTurn();
		assertEquals("can not move black in wrong direction", false, game.move(Location.R12, Location.R10));

	}

	@Test
	public void illegalRedMove() {
		game.nextTurn();
		game.nextTurn();
		
		assertEquals("can not move black in wrong direction", false, game.move(Location.R8, Location.R10));
	}

	@Test
	public void shouldbeAbleToMove1OnTurnOne() {

		game.nextTurn();
		
		assertEquals(false, game.move(Location.R1, Location.R5));
		
		assertEquals(true, game.move(Location.R1, Location.R2));
		
		
	}

	@Test
	public void shouldbeAbleToMove2OnTurnOne() {

		game.nextTurn();
		assertEquals(true, game.move(Location.R1, Location.R3));
	}

	@Test
	public void shouldMove1AfterMove2() {

		game.nextTurn();
		assertEquals(true, game.move(Location.R1, Location.R3));
		
		assertEquals(true, game.move(Location.R1, Location.R2));
		
	}

	@Test
	public void shouldResetDie() {
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		
		game.newGame();
		
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R2));
		assertTrue(game.move(Location.R1, Location.R3));
	}



	@Test
	public void shouldBeInOrderOfDice() {

		game.nextTurn();

		assertTrue(game.move(Location.B8, Location.B7));
		assertTrue(game.move(Location.R12, Location.B11));
		
		game.nextTurn();
		
		assertTrue(game.move(Location.R8, Location.R5));
		assertTrue(game.move(Location.B12, Location.R9));
		
		game.nextTurn();
		
		assertTrue(game.move(Location.B8, Location.B3));
		assertTrue(game.move(Location.R1, Location.R7));
	}
	@Test
	public void diceShouldBe6and5() {

		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		
		assertEquals("Should be 6", 6, game.diceValuesLeft()[0]);
		assertEquals("Should be 5", 5, game.diceValuesLeft()[1]);
		
	}

	
	@Test
	public void diceShouldBe4and3() {

		game.nextTurn();
		game.nextTurn();
		
		assertEquals("Should be 4", 4, game.diceValuesLeft()[0]);
		assertEquals("Should be 3", 3, game.diceValuesLeft()[1]);
		
	}

	@Test
	public void diceShouldBe2and1() {

		game.nextTurn();
		
		assertEquals("Should be 2", 2, game.diceValuesLeft()[0]);
		assertEquals("Should be 1", 1, game.diceValuesLeft()[1]);
		
	}


	@Test
	public void canNotMoveSelfToBar() {

		game.configure(new Placement[] { 
				
				new Placement(Color.BLACK, Location.R1),
				new Placement(Color.BLACK, Location.R1),
				new Placement(Color.BLACK, Location.R2),

		});
		game.nextTurn();

		assertTrue(game.move(Location.R1, Location.R2));
		
		assertTrue(game.getColor(Location.R2) == Color.BLACK);
		assertTrue(game.getCount(Location.B_BAR) == 0);
		assertTrue(game.getCount(Location.R_BAR) == 0);
		
		assertTrue(game.getCount(Location.R1) == 1);
		assertTrue(game.getCount(Location.R2) == 2);

	}


	@Test
	public void canNotMoveToBarWhenMoreThan1() {
		game.configure(new Placement[] { 
				
				new Placement(Color.RED, Location.R2),
				new Placement(Color.RED, Location.R2),
				new Placement(Color.BLACK, Location.R1),
		});
		game.nextTurn();
		assertFalse(game.move(Location.R1, Location.R2));
	}


	@Test
	public void canNotMakeMoveToBar() {

		game.configure(new Placement[] { 
				new Placement(Color.BLACK, Location.B1),
		});
		game.nextTurn();
		
		assertEquals(false, game.move(Location.B1, Location.R_BAR));
		
	}

	@Test
	public void moveToCorrectInnerTable() {

		game.configure(new Placement[] { 
				new Placement(Color.RED, Location.R_BAR),
				
		});

		game.nextTurn();
		game.nextTurn();
		
		assertTrue(game.move(Location.R_BAR, Location.B3));
	}


	@Test
	public void illegalBarMove() {
		game.configure(new Placement[] { 
				new Placement(Color.RED, Location.R_BAR),
				new Placement(Color.BLACK, Location.B3),	
		});

		game.nextTurn();
		game.nextTurn();
		assertFalse(game.move(Location.R_BAR, Location.B3));
	}

	
	@Test
	public void shouldMoveBlackCheckerToTheBarWhenRedMoves() {

		game.configure(new Placement[] { 
				new Placement(Color.BLACK, Location.B4),
				new Placement(Color.RED, Location.B1),
				new Placement(Color.RED, Location.B1)}
				);

		game.nextTurn();
		game.nextTurn();
		assertTrue(game.move(Location.B1, Location.B4));
		
		assertEquals(1,game.getCount(Location.B4) );
		assertTrue(game.getColor(Location.B4) == Color.RED);
		assertEquals(1,game.getCount(Location.B_BAR) );
	}

	@Test
	public void shouldMoveRedCheckerToTheBarWhenBlackMoves() {

		game.configure(new Placement[] { 
				new Placement(Color.RED, Location.R2),
				new Placement(Color.BLACK, Location.R1),
				new Placement(Color.BLACK, Location.R1),
		});
		game.nextTurn();

		assertTrue(game.move(Location.R1, Location.R2));
		assertTrue(game.getCount(Location.R1) == 1);
		assertTrue(game.getCount(Location.R2) == 1);
		assertTrue(game.getColor(Location.R2) == Color.BLACK);
		assertTrue(game.getCount(Location.B_BAR) == 0);
		assertTrue(game.getCount(Location.R_BAR) == 1);

	}
	
	@Test
	public void movingToBarIncreasesNumberOfCheckersInThisBarBy1() {

		game.configure(new Placement[] { 
				new Placement(Color.RED, Location.R2),
				new Placement(Color.BLACK, Location.R1),
				new Placement(Color.BLACK, Location.R1),
				new Placement(Color.RED, Location.R_BAR)	
		});
		game.nextTurn();
		assertEquals("should be 1 ", 1, game.getCount(Location.R2));

		assertTrue(game.move(Location.R1, Location.R2));
		
		assertEquals("should be 2 ", 2, game.getCount(Location.R_BAR));

	}

}

