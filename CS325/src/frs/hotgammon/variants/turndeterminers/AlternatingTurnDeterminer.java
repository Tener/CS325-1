package frs.hotgammon.variants.turndeterminers;

import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.TurnDeterminer;
import frs.hotgammon.common.GameImpl;

public class AlternatingTurnDeterminer implements TurnDeterminer {

	Game game;
	
	@Override
	public Color nextTurnChangePlayer(Color colorInTurn, GameImpl gameImpl) {
		
		
		return (colorInTurn != Color.BLACK) ? Color.BLACK : Color.RED;

	}

	@Override
	public void setGame(Game game) {
		this.game = game;
		
	}

}
