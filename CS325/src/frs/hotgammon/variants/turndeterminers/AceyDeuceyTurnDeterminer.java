package frs.hotgammon.variants.turndeterminers;

import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.TurnDeterminer;
import frs.hotgammon.common.GameImpl;

public class AceyDeuceyTurnDeterminer implements TurnDeterminer {

	Game game; 
	
	@Override
	public Color nextTurnChangePlayer(Color colorInTurn, GameImpl gameImpl) {
		
		final int DICE_ONE = 1;
		final int DICE_TWO = 2;
		System.out.println("this is printing");
		
			
			if((gameImpl.diceThrown()[0] == DICE_ONE) && (gameImpl.diceThrown()[1] == DICE_TWO)){
				return gameImpl.getPlayerInTurn();
			}
		
		
		return (colorInTurn != Color.BLACK) ? Color.BLACK : Color.RED;
	}

	@Override
	public void setGame(Game game) {
		this.game = game;
		
	}

}
