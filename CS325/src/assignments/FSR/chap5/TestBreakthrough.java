package assignments.FSR.chap5;

import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Initial test case class for Breakthrough
 * 
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 * 
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class TestBreakthrough {
	Breakthrough game;

	/** Fixture */
	@Before
	public void setUp() {
		game = new BreakthroughImpl();
	}

	@Test
	public void shouldHaveBlackPawnOn00() {
		
		game.placeBlackPieceOnBoard(0, 0);
		
		assertEquals("Black has pawn on (0,0)", BreakthroughImpl.PieceType.BLACK, game.getPieceAt(0, 0));
	}
	
	@Test
	public void shouldHaveWhitePawnOn77() {
		
		game.placeWhitePieceOnBoard(7, 7);
		
		assertEquals("White has pawn on (7,7)", BreakthroughImpl.PieceType.WHITE, game.getPieceAt(7, 7));
	}
	
	@Test
	public void shouldHaveFullBeginingBoard() {
		
		game.fillBoardForGameStart();
		
		assertEquals("Black has pawn on (0,5)", BreakthroughImpl.PieceType.BLACK, game.getPieceAt(0, 5));
		assertEquals("Black has pawn on (0,3)", BreakthroughImpl.PieceType.BLACK, game.getPieceAt(0, 3));
		assertEquals("Black has pawn on (1,2)", BreakthroughImpl.PieceType.BLACK, game.getPieceAt(1, 2));
		assertEquals("Black has pawn on (1,7)", BreakthroughImpl.PieceType.BLACK, game.getPieceAt(1, 7));
		
		assertEquals("White has pawn on (7,3)", BreakthroughImpl.PieceType.WHITE, game.getPieceAt(7, 3));
		assertEquals("White has pawn on (6,0)", BreakthroughImpl.PieceType.WHITE, game.getPieceAt(6, 0));
		assertEquals("White has pawn on (7,4)", BreakthroughImpl.PieceType.WHITE, game.getPieceAt(7, 4));
		assertEquals("White has pawn on (6,1)", BreakthroughImpl.PieceType.WHITE, game.getPieceAt(6, 1));
		
		assertEquals("No one at (4,4)", BreakthroughImpl.PieceType.NONE, game.getPieceAt(4, 4));
	}
	
	@Test
	public void shouldBeWhiteToGoFirst() {
		
		assertEquals("White makes first move", Breakthrough.PlayerType.WHITE, game.getPlayerInTurn());
	}

	@Test
	public void shouldMakeAValidMoveWithNoAttack() {
		
		game.fillBoardForGameStart();
		
		assertEquals("Move is valid", true, game.isMoveValid(6, 1, 5, 1));
		assertEquals("Move is not valid", false, game.isMoveValid(6, 1, 5, 2));
		assertEquals("Move is not valid", false, game.isMoveValid(6, 1, 5, 0));
		assertEquals("Move is not valid", false, game.isMoveValid(6, 1, 6, 2));
		assertEquals("Move is not valid", false, game.isMoveValid(6, 1, 7, 1));
	}
	
	@Test
	public void shouldMakeAMove() {
		
		game.fillBoardForGameStart();
		
		if(game.isMoveValid(6, 1, 5, 1) == true){
			game.move(6, 1, 5, 1);
		}
		
		assertEquals("No piece where it was",BreakthroughImpl.PieceType.NONE , game.getPieceAt(6, 1));
		assertEquals("No piece where it was", BreakthroughImpl.PieceType.WHITE, game.getPieceAt(5, 1));

	}
	
	@Test
	public void shouldMakeAValidMoveWithAnAttack() {
		
		game.fillBoardForGameStart();
		
		game.placeBlackPieceOnBoard(5, 2);
		
		assertEquals("Move is valid", true, game.isMoveValid(6, 1, 5, 2));
		
	}
	
	@Test
	public void shouldMakeAMoveWithAnAttack() {
		
		game.fillBoardForGameStart();
		
		game.placeBlackPieceOnBoard(5, 2);
		
		if(game.isMoveValid(6, 1, 5, 2) == true){
			game.move(6, 1, 5, 2);
		}
		
		assertEquals("No piece where it was",BreakthroughImpl.PieceType.NONE , game.getPieceAt(6, 1));
		assertEquals("No piece where it was", BreakthroughImpl.PieceType.WHITE, game.getPieceAt(5, 2));
		
	}
	
	@Test
	public void shouldTogglePlayer() {
		
		game.fillBoardForGameStart();
		
		assertEquals("PlayerType is WHITE",Breakthrough.PlayerType.WHITE , game.getPlayerInTurn());
		
		if(game.isMoveValid(6, 1, 5, 1) == true){
			game.move(6, 1, 5, 1);
		}
		
		assertEquals("PlayerType is BLACK",Breakthrough.PlayerType.BLACK , game.getPlayerInTurn());

	}
	
	@Test
	public void shouldNotPlayOutOfTurn() {
		
		game.fillBoardForGameStart();
		
		assertEquals("PlayerType is WHITE",Breakthrough.PlayerType.WHITE , game.getPlayerInTurn());
		
		if(game.isMoveValid(6, 1, 5, 1) == true){
			game.move(6, 1, 5, 1);
		}
		
		assertEquals("PlayerType is BLACK",Breakthrough.PlayerType.BLACK , game.getPlayerInTurn());
		
		assertEquals("Move is not valid", false, game.isMoveValid(6, 6, 5, 6));

	}
	
	@Test
	public void shouldWhiteWin() {
		
		game.fillBoardForGameStart();
		
		game.placeWhitePieceOnBoard(0, 7);
		
		if(game.isMoveValid(6, 1, 5, 1) == true){
			game.move(6, 1, 5, 1);
		}
		
		assertEquals("White is winner", Breakthrough.PlayerType.WHITE, game.getWinner());
		
		
	}
	
	@Test
	public void shouldBlackWin() {
		
		game.fillBoardForGameStart();
		
		game.placeBlackPieceOnBoard(7, 7);
		
		if(game.isMoveValid(6, 1, 5, 1) == true){
			game.move(6, 1, 5, 1);
		}
		
		assertEquals("Black is winner", Breakthrough.PlayerType.BLACK, game.getWinner());
		
		
	}
}
