package frs.hotgammon.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.variants.AlphaMoveValidator;
import frs.hotgammon.variants.DelatTurnChangeValidator;
import frs.hotgammon.variants.GammaWinnerValidator;

public class DeltaMonTests {

	Game game;

	@Before
	public void setup(){
		game=new GameImpl(new AlphaMoveValidator(),new GammaWinnerValidator(),new DelatTurnChangeValidator());
		game.newGame();
	}

	@Test
	public void shouldNotGiveExtraTurn(){
		game.nextTurn();
		assertEquals("First time color is black ", Color.BLACK, game.getPlayerInTurn());
		
		game.nextTurn();
		game.nextTurn();
		
		assertEquals("THird time color is black ", Color.RED, game.getPlayerInTurn());
	}
	
	@Test
	public void shouldGiveExtraTurn() {
		game.nextTurn();
		assertEquals("First time color is black ", Color.BLACK, game.getPlayerInTurn());
		
		game.nextTurn();
		assertEquals("Second time color is black ", Color.BLACK, game.getPlayerInTurn());
	}
	

}
