package frs.hotgammon.testsMine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.Location;
import frs.hotgammon.common.GameImpl.Placement;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.variants.factory.GammaFactory;
import frs.hotgammon.variants.movevalidators.SimpleMoveValidator;
import frs.hotgammon.variants.turndeterminers.AlternatingTurnDeterminer;
import frs.hotgammon.variants.winnerdeterminers.BearOffWinnerDeterminer;
import frs.hotgammon.variants.winnerdeterminers.SixMoveWinnerDeterminer;

public class GammaMonTests {

	GameImpl game;
	
	@Before
	public void setUp() {
		game = new GameImpl(new GammaFactory());
		game.newGame();
	}
	
	@Test
	public void gameDoesNotEnd(){
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		assertEquals("Should not be red ", Color.NONE, game.winner());
	}
	
	@Test
	public void blackShouldBeTheWinner() {

		game.configure(new Placement[] { 
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF)}
				);
		game.nextTurn();
		
		assertEquals("Should not black ", Color.BLACK, game.winner());
	}
	
	@Test
	public void redShouldBeTheWinner() {

		game.configure(new Placement[] { 
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF)}
				); 
		game.nextTurn();
		
		assertEquals("Should not red ", Color.RED, game.winner());
	}
	
	@Test
	public void noOneShouldBeTheWinner() {

		game.configure(new Placement[] { 
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF)}
				);
		game.nextTurn();
		
		assertEquals("Should not be red or black ", Color.NONE, game.winner());
	}

}
