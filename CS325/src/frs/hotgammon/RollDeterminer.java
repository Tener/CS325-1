package frs.hotgammon;

import frs.hotgammon.common.GameImpl;

public interface RollDeterminer {
	public void setGame(Game game);
	public int[] diceThrown(GameImpl game);

}
