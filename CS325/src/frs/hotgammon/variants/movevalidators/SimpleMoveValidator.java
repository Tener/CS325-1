package frs.hotgammon.variants.movevalidators;

import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.Location;
import frs.hotgammon.MoveValidator;
import frs.hotgammon.common.GameImpl;

public class SimpleMoveValidator implements MoveValidator{

	private GameImpl game;
	public SimpleMoveValidator(){
	}
	@Override
	public boolean isValid(Location from, Location to) {
		if( (game.getColor(from) == game.getColor(to) || game.getColor(to) == Color.NONE) && (game.getPlayerInTurn() == game.getColor(from)) ){
			
			System.out.println("returning true");
			return true;
			  
		}
		return false;
	}
	@Override
	public void setGame(GameImpl game) {
		this.game = game;
		
	}
	

}
