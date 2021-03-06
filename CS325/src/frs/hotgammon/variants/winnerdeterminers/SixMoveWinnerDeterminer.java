package frs.hotgammon.variants.winnerdeterminers;

import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.WinnerDeterminer;

public class SixMoveWinnerDeterminer implements WinnerDeterminer{

	Game game;
	int turnsMade;
	@Override
	public Color returnWinner() {
		
		if (turnsMade < 6)
			return Color.NONE;
		else
			return Color.RED;
	}

	@Override
	public void setGame(Game game) {
		this.game = game;
		
	}

	@Override
	public void makeATurn() {
		turnsMade = turnsMade + 1;
		
	}

}
