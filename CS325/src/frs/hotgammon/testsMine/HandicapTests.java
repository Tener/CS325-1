package frs.hotgammon.testsMine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import frs.hotgammon.Color;
import frs.hotgammon.Location;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.common.GameImpl.Placement;
import frs.hotgammon.variants.factory.AlphaFactory;
import frs.hotgammon.variants.factory.BetaFactory;
import frs.hotgammon.variants.factory.DeltaFactory;
import frs.hotgammon.variants.factory.HandicapFactory;

public class HandicapTests {
	
	GameImpl game;

	@Before
	public void setup(){
		game=new GameImpl(new HandicapFactory((new AlphaFactory()), (new BetaFactory())));
		game.newGame();
	}
	
	@Test
	public void ShouldBeBlackToGoFirst() {

		game.nextTurn();

		assertEquals("Black is first to go", Color.BLACK,
				game.getPlayerInTurn());

	}
	
	@Test
	public void ShouldBeRedToGoSecond() {

		game.nextTurn();
		game.nextTurn();

		assertEquals("Red is second to go", Color.RED,
				game.getPlayerInTurn());

	}

	@Test
	public void playersMoveByOwnRules() {

		game.nextTurn();
		assertEquals(true, game.move(Location.R1, Location.R2));
		
		game.nextTurn();
		
		assertEquals(true, game.move(Location.B12, Location.R10));
		
		assertEquals(true, game.move(Location.R6, Location.R2));

	}
	
	@Test
	public void playersDontMoveByDifferentRules() {

		game.nextTurn();
		game.nextTurn();
		assertEquals(false, game.move(Location.B1, Location.B2)); 
		

	}

}
