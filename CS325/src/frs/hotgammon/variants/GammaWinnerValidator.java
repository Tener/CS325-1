package frs.hotgammon.variants;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.Location;
import frs.hotgammon.WinnerValidator;

public class GammaWinnerValidator implements WinnerValidator{

	Game game;
	@Override
	public Color returnWinner() {
		if (game.getCount(Location.B_BEAR_OFF) == 15)
			return Color.BLACK;
		else if (game.getCount(Location.R_BEAR_OFF) == 15)
			return Color.RED;
		else
			return Color.NONE;
	}

	@Override
	public void setGame(Game game) {
		this.game = game;
		
	}

	@Override
	public void makeATurn() {
		// TODO Auto-generated method stub
		
	}
	

}
