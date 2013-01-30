package assignments.FSR.chap5;

/**
 * Implementation stub.
 * 
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 * 
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class BreakthroughImpl implements Breakthrough {

	public static final int BOARD_HEIGHT = 8;
	public static final int BOARD_WIDTH = 8;
	public static final int WHITE_HOMEROW = 0;
	public static final int BLACK_HOMEROW = 7;
	public PlayerType playerInTurn = PlayerType.WHITE;
	public PlayerType currentWinner = null;

	private PieceType[][] Board = new PieceType[BOARD_HEIGHT][BOARD_WIDTH];

	public PieceType getPieceAt(int row, int column) {
		if (Board[row][column] == Breakthrough.PieceType.BLACK
				|| Board[row][column] == Breakthrough.PieceType.WHITE) {
			return Board[row][column];
		}

		else {
			return PieceType.NONE;
		}

	}

	public PlayerType getPlayerInTurn() {
		return playerInTurn;
	}

	public PlayerType getWinner() {
		return currentWinner;
	}

	public boolean isMoveValid(int fromRow, int fromColumn, int toRow,
			int toColumn) {

		if (getPieceAt(fromRow, fromColumn).toString() == playerInTurn
				.toString()) {

			if ((fromColumn == toColumn && this.getPieceAt(toRow, toColumn) == PieceType.NONE)) {
				return true;
			}
			if ((toColumn == fromColumn + 1 || toColumn == fromColumn - 1)
					&& ((this.getPieceAt(toRow, toColumn) != PieceType.NONE) && (this
							.getPieceAt(toRow, toColumn) != this.getPieceAt(
							fromRow, fromColumn)))) {
				return true;
			}
		}

		return false;
	}

	public void move(int fromRow, int fromColumn, int toRow, int toColumn) {
		Breakthrough.PieceType pieceToMove = this.getPieceAt(fromRow,
				fromColumn);

		Board[fromRow][fromColumn] = PieceType.NONE;
		Board[toRow][toColumn] = pieceToMove;
		this.isThereAWinner();
		if(currentWinner != null){
			System.out.println(" The Winner is: " + this.getWinner() + "!");
			return;
		}
		this.togglePlayer();
		
	}

	private void isThereAWinner() {
		for(int i = 0; i < 8; i++){
			if(this.getPieceAt(WHITE_HOMEROW, i) == PieceType.WHITE){
				currentWinner = PlayerType.WHITE;
			}
		}
		for(int i = 0; i < 8; i++){
			if(this.getPieceAt(BLACK_HOMEROW, i) == PieceType.BLACK){
				currentWinner = PlayerType.BLACK;
			}
		}
		
	}

	public void togglePlayer() {
		if (playerInTurn == PlayerType.WHITE) {
			playerInTurn = PlayerType.BLACK;
			return;
		}
		if (playerInTurn == PlayerType.BLACK) {
			playerInTurn = PlayerType.WHITE;
			return;
		}
	}

	@Override
	public void placeBlackPieceOnBoard(int row, int col) {
		Board[row][col] = Breakthrough.PieceType.BLACK;

	}

	@Override
	public void placeWhitePieceOnBoard(int row, int col) {
		Board[row][col] = Breakthrough.PieceType.WHITE;

	}

	@Override
	public void fillBoardForGameStart() {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 8; j++) {
				placeBlackPieceOnBoard(i, j);
			}
		}

		for (int i = 6; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				placeWhitePieceOnBoard(i, j);
			}
		}

	}
}
