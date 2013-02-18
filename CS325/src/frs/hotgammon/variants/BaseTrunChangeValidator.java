package frs.hotgammon.variants;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.TurnChangeValidator;

public class BaseTrunChangeValidator implements TurnChangeValidator {

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
