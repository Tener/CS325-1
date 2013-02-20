package frs.hotgammon.variants.turndeterminers;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.TurnDeterminer;

public class AceyDeuceyTurnDeterminer implements TurnDeterminer {

	Game game; 
	
	@Override
	public Color nextTurnChangePlayer(Color colorInTurn) {
		
		final int DICE_ONE = 1;
		final int DICE_TWO = 2;
		
		
			
			if((game.diceThrown()[0] == DICE_ONE) && (game.diceThrown()[1] == DICE_TWO)){
				return game.getPlayerInTurn();
			}
		
		
		return (colorInTurn != Color.BLACK) ? Color.BLACK : Color.RED;
	}

	@Override
	public void setGame(Game game) {
		this.game = game;
		
	}

}
