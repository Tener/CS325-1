package frs.hotgammon.variants.winnerdeterminers;

import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.Location;
import frs.hotgammon.WinnerDeterminer;

public class BearOffWinnerDeterminer implements WinnerDeterminer{

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
