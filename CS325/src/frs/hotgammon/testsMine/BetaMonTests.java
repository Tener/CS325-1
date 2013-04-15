package frs.hotgammon.testsMine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.Location;
import frs.hotgammon.common.GameImpl.Placement;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.variants.factory.BetaFactory;
import frs.hotgammon.variants.movevalidators.CompleteMoveValidator;
import frs.hotgammon.variants.turndeterminers.AlternatingTurnDeterminer;
import frs.hotgammon.variants.winnerdeterminers.SixMoveWinnerDeterminer;

public class BetaMonTests {

	GameImpl game;
	
	@Before
	public void setup() {
		game = new GameImpl(new BetaFactory());
		game.newGame();
	}
	
	@Test
	public void illegalBlackMove() {
		game.nextTurn();
		assertEquals("can not move black in wrong direction", false, game.move(Location.B8, Location.B10));

	}

	@Test
	public void illegalRedMove() {
		game.nextTurn();
		game.nextTurn();
		
		assertEquals("can not move black in wrong direction", false, game.move(Location.B12, Location.B10));
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
	public void shouldNotMoveSelfToBar() {

		game.configure(new Placement[] { 
				
				new Placement(Color.BLACK, Location.R1),
				new Placement(Color.BLACK, Location.R2),

		});
		game.nextTurn();

		assertEquals(true, game.move(Location.R1, Location.R2));
		
		
		assertEquals(0, game.getCount(Location.B_BAR));
		
		

	}


	@Test
	public void canNotMoveToBarWhenMoreThan1() {
		game.configure(new Placement[] { 
				
				new Placement(Color.BLACK, Location.R1),
				new Placement(Color.RED, Location.R2),
				new Placement(Color.RED, Location.R2),
				new Placement(Color.RED, Location.R2),
				new Placement(Color.RED, Location.R2),
				new Placement(Color.RED, Location.R2),
				new Placement(Color.RED, Location.R2),
				new Placement(Color.RED, Location.R2),
		});
		game.nextTurn();
		assertEquals(false, game.move(Location.R1, Location.R2));
	}


	@Test
	public void moveToCorrectInnerTable() {

		game.configure(new Placement[] { 
				new Placement(Color.RED, Location.R_BAR),
				
		});

		game.nextTurn();
		game.nextTurn();
		
		assertEquals(true, game.move(Location.R_BAR, Location.B3));
	}
	
	@Test
	public void shouldMoveBlackToBar() {

		game.configure(new Placement[] { 
				new Placement(Color.BLACK, Location.B3),
				new Placement(Color.RED, Location.B1),
				new Placement(Color.RED, Location.B1),
				new Placement(Color.RED, Location.B1),
				new Placement(Color.RED, Location.B1)
		});

		game.nextTurn();
		game.nextTurn();
		
		assertEquals(true, game.move(Location.B1, Location.B3));
		
		
		assertEquals(1,game.getCount(Location.B_BAR) );
	}

	@Test
	public void shouldMoveRedToBar() {

		game.configure(new Placement[] { 
				new Placement(Color.RED, Location.R2),
				new Placement(Color.BLACK, Location.R1),
				new Placement(Color.BLACK, Location.R1),
				new Placement(Color.BLACK, Location.R1),
				new Placement(Color.BLACK, Location.R1)
		});
		game.nextTurn();

		assertEquals(true, game.move(Location.R1, Location.R2));
	
		
		assertEquals(1, game.getCount(Location.R_BAR));

	}
	

}

