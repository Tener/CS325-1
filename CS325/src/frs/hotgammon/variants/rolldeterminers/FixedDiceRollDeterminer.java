package frs.hotgammon.variants.rolldeterminers;

import frs.hotgammon.Game;
import frs.hotgammon.RollDeterminer;
import frs.hotgammon.common.GameImpl;

public class FixedDiceRollDeterminer implements RollDeterminer {

	
	Game game;
	
	@Override
	public int[] diceThrown(GameImpl game) {
		if (game.numberOfTurns % 3 == 1) {
			game.currentDice = new int[] { 1, 2 };
		}

		if (game.numberOfTurns % 3 == 2) {
			game.currentDice = new int[] { 3, 4 };
		}

		if (game.numberOfTurns % 3 == 0) {
			game.currentDice = new int[] { 5, 6 };
		}

		return game.currentDice;
	}
	@Override
	public void setGame(Game game) {
		this.game = game;
		
	}

}
