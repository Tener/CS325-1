package frs.hotgammon.variants.turndeterminers;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.TurnDeterminer;

public class AlternatingTurnDeterminer implements TurnDeterminer {

	Game game;
	
	@Override
	public Color nextTurnChangePlayer(Color colorInTurn) {
		return (colorInTurn != Color.BLACK) ? Color.BLACK : Color.RED;

	}

	@Override
	public void setGame(Game game) {
		this.game = game;
		
	}

}
