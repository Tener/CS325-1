package frs.hotgammon.variants.movevalidators;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.Location;
import frs.hotgammon.MoveValidator;
import frs.hotgammon.common.BoardImpl;

public class CompleteMoveValidator implements MoveValidator {
	
	private Game game;

	public CompleteMoveValidator() {
	}

	@Override
	public void setGame(Game game) {
		
		this.game = game;
	}
	
	private boolean checkerMovedToEmptyPoint(Location to){
		if( game.getColor(to) == Color.NONE ){
			if (game.getCount(to) == 0){
				return true;
			}
			return false;
		}
		
		return false;
	}

	private boolean legalDirectionMove(Location from, Location to) {

		if(game.getPlayerInTurn() == Color.RED && Location.distance(from, to) < 0){
			return true;
		}
		if(game.getPlayerInTurn() == Color.BLACK && Location.distance(from, to) > 0){
			return true;
		}
		return false;

	}
	
	private boolean moveToLegal(Location to){
		
		if(checkerMovedToEmptyPoint(to) || (game.getColor(to) == game.getPlayerInTurn()) || (game.getCount(to) == 1 && (game.getColor(to) != game.getPlayerInTurn()))){
			return true;
		}
		return false;
	}


	public boolean distanceIsDieValue(Location from,
			Location to) {

		if (game.diceValuesLeft()[0] == Math.abs(Location.distance(from, to))) {
			return true;
		}
		if (game.diceValuesLeft()[1] == Math.abs(Location.distance(from, to))) {
			return true;
		}

		return false;

	}

	private boolean moveDirectToBarIsIllegal(Location to) {

		if(to == Location.B_BAR){
			return false;
		}
		if(to == Location.R_BAR){
			return false;
		}
		return true;
	}

	
	@Override
	public boolean isValid(Location from, Location to) {
		return (moveToLegal(to)) && legalDirectionMove(from, to) && (moveDirectToBarIsIllegal(to)) && distanceIsDieValue(from, to);
				
	}
}
