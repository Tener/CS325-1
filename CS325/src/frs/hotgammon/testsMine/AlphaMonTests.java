package frs.hotgammon.testsMine;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.Location;
import frs.hotgammon.MoveValidator;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.variants.factory.AlphaFactory;
import frs.hotgammon.variants.movevalidators.SimpleMoveValidator;
import frs.hotgammon.variants.turndeterminers.AlternatingTurnDeterminer;
import frs.hotgammon.variants.winnerdeterminers.SixMoveWinnerDeterminer;

public class AlphaMonTests {
	
	Game game;

	@Before
	public void setUp() {
		game = new GameImpl(new AlphaFactory());
		game.newGame();
	}

	@Test
	public void ShouldBeBlackToGoFirst() {

		game.nextTurn();

		assertEquals("Black is first to go", Color.BLACK,
				game.getPlayerInTurn());

	}

	@Test
	public void shouldRunOutOfMoves() {
		// Needed to add nextTurn call
		game.nextTurn();
		game.move(Location.R1, Location.R2);

		game.move(Location.R1, Location.R2);

		assertEquals(" no moves should be left ", 0,
				game.getNumberOfMovesLeft());

	}
	@Test
	public void shouldGetNextTurnAndDiceBe34(){
		
		game.nextTurn();
		game.nextTurn();
		
		int [] currentDice = game.diceThrown();
		
		int firstDie = currentDice[0];
		int secondDie = currentDice[1];
		
		System.out.println(firstDie + " " + secondDie);
		
		assertEquals( "dice should be 3-4", 3, firstDie);
		assertEquals( "dice should be 3-4", 4, secondDie);
	}
	
	@Test
	public void shouldGetNextTurnThreeTimesAndDiceBe56(){
		
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		
		
		int [] currentDice = game.diceThrown();
		
		int firstDie = currentDice[0];
		int secondDie = currentDice[1];
		
		System.out.println(firstDie + " " + secondDie);
		
		assertEquals( "dice should be 5-6", 5, firstDie);
		assertEquals( "dice should be 5-6", 6, secondDie);
	}
	
	@Test
	public void shouldGetDice1And2OnOpeningTurn(){
		
		game.nextTurn();
		
		int [] currentDice = game.diceThrown();
		
		int firstDie = currentDice[0];
		int secondDie = currentDice[1];
		
		assertEquals( "dice should be 1-2", 1, firstDie);
		assertEquals( "dice should be 1-2", 2, secondDie);
	}

	@Test
	public void shouldEndGameAfter6Rolls() {

		game.nextTurn();

		game.nextTurn();

		assertEquals("should not be a winner ", Color.NONE, game.winner());

		game.nextTurn();

		game.nextTurn();

		game.nextTurn();

		game.nextTurn();

		assertEquals("should not be a winner ", Color.RED, game.winner());

	}

	@Test
	public void shouldEndGameAfter5Rolls() {

		game.nextTurn();

		assertEquals("should not be a winner ", Color.NONE, game.winner());

		game.nextTurn();

		game.nextTurn();

		game.nextTurn();

		assertEquals("should not be a winner ", Color.NONE, game.winner());

	}
	

}
