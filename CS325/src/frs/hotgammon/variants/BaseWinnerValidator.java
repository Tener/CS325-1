package frs.hotgammon.variants;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.WinnerValidator;

public class BaseWinnerValidator implements WinnerValidator{

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
