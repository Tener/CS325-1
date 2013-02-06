package frs.hotgammon.alphamon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameTests {

	Game game;
	
	@Before
	public void setUp() {
		game = new GameImpl();
	}
	
	@Test
	public void ShouldBeBlackToGoFirst() {
		
		game.nextTurn();
		
		assertEquals("Black is first to go", Color.BLACK, game.getPlayerInTurn());
	}
	
	@Test
	public void shouldRunOutOfMoves(){
		
		game.newGame();
		
		game.makeMove(Location.R1, Location.R2);
		game.makeMove(Location.R1, Location.R2);
		
		assertEquals(" no moves should be left ", 0, game.getNumberOfMovesLeft());
		
	}
	
	@Test
	public void shouldGetNextTurnAndDiceBe34(){
		game.newGame();
		
		game.nextTurn();
		
		int [] currentDice = game.diceValuesLeft();
		
		int firstDie = currentDice[0];
		int secondDie = currentDice[1];
		
		System.out.println(firstDie + " " + secondDie);
		
		assertEquals( "dice should be 3-4", 3, secondDie);
		assertEquals( "dice should be 3-4", 4, firstDie);
	}
	
	@Test
	public void shouldGetNextTurnTwiceAndDiceBe56(){
		game.newGame();
		
		game.nextTurn();
		game.nextTurn();
		
		int [] currentDice = game.diceValuesLeft();
		
		int firstDie = currentDice[0];
		int secondDie = currentDice[1];
		
		System.out.println(firstDie + " " + secondDie);
		
		assertEquals( "dice should be 5-6", 5, secondDie);
		assertEquals( "dice should be 5-6", 6, firstDie);
	}
	
	@Test
	public void shouldGetDice1And2OnOpeningTurn(){
		game.newGame();
		
		
		int [] currentDice = game.diceValuesLeft();
		
		int firstDie = currentDice[0];
		int secondDie = currentDice[1];
		
		assertEquals( "dice should be 1-2", 1, secondDie);
		assertEquals( "dice should be 1-2", 2, firstDie);
	}
	
	@Test
	public void shouldEndGameAfter6Rolls(){
		
		game.newGame();
		
		game.nextTurn();
		
		assertEquals( "should not be a winner ", Color.NONE, game.winner());
		
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		
		assertEquals( "should not be a winner ", Color.RED, game.winner());

		
	}
	
	@Test
	public void shouldEndGameAfter5Rolls(){
		
		game.newGame();
		
		game.nextTurn();
		
		assertEquals( "should not be a winner ", Color.NONE, game.winner());
		
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		
		assertEquals( "should not be a winner ", Color.NONE, game.winner());

		
	}

}
