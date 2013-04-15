package frs.hotgammon;

import frs.hotgammon.common.GameImpl;
import frs.hotgammon.framework.Game;

public interface RollDeterminer {
	public void setGame(Game game);
	public int[] diceThrown(GameImpl game);

}
