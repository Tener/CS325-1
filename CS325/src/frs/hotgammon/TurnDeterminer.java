package frs.hotgammon;

import frs.hotgammon.common.GameImpl;
import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;

public interface TurnDeterminer {

	public Color nextTurnChangePlayer(Color colorInTurn, GameImpl gameImpl);
	public void setGame(Game game);
}
