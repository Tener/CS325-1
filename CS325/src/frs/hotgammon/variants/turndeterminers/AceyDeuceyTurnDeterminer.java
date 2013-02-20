package frs.hotgammon.variants.turndeterminers;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.TurnDeterminer;

public class AceyDeuceyTurnDeterminer implements TurnDeterminer {

	Game game; 
	
	@Override
	public Color nextTurnChangePlayer(Color colorInTurn) {
		boolean firstDie = false;
		boolean secondDie = false;
		
		final int DICE_ONE = 1;
		final int DICE_TWO = 2;
		
		for(int i = 0; i < game.diceThrown().length; i++){
			
			if(game.diceThrown()[i] == DICE_ONE){
				
				firstDie=true;
			}
			if(game.diceThrown()[i] == DICE_TWO){
				
				secondDie=true;
			}
		}
		if(firstDie && secondDie){
			return game.getPlayerInTurn();
		}
		return colorInTurn==Color.BLACK?Color.RED:Color.BLACK;
	}

	@Override
	public void setGame(Game game) {
		this.game = game;
		
	}

}
