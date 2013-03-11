package frs.hotgammon;

import frs.hotgammon.common.GameImpl;

public interface TurnDeterminer {

	public Color nextTurnChangePlayer(Color colorInTurn, GameImpl gameImpl);
	public void setGame(Game game);
}
