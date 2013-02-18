package frs.hotgammon.variants;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.Location;
import frs.hotgammon.MoveValidator;

public class AlphaMoveValidator implements MoveValidator{

	private Game game;
	public AlphaMoveValidator(){
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
	public void setGame(Game game) {
		this.game = game;
		
	}
	

}
