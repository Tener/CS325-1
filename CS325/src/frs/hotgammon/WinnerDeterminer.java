package frs.hotgammon;

import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;

public interface WinnerDeterminer {

	public Color returnWinner();
	public void setGame(Game game);
	public void makeATurn();
}
