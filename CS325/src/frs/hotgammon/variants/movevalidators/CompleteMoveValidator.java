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

	private boolean sameColorPoint(Location to) {
		if(game.getColor(to) == game.getPlayerInTurn()){
			return true;
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
		
		if(checkerMovedToEmptyPoint(to) || sameColorPoint(to) || moveToPointWith1(to)){
			return true;
		}
		return false;
	}


	public boolean distanceIsDieValue(Location from,
			Location to) {

		int distance = Location.distance(from, to);

		for (int i = 0; i < game.diceValuesLeft().length; i++) {
			if (game.diceValuesLeft()[i] == Math.abs(distance)) {
				return true;
			}
		}
		return false;

	}

	public boolean moveToPointWith1(Location to) {
		if((game.getCount(to) == 1 && (game.getColor(to) != game.getPlayerInTurn()))){
			return true;
		}
		return false;
	}

	private boolean moveDirectToBarIsIllegal(Location to) {

		if((to == Location.R_BAR) || (to == Location.B_BAR)){
			return false;
		}
		return true;
	}

	private boolean checkerInBar() {
		
		if (( game.getCount(Location.B_BAR) != 0 && game.getPlayerInTurn() == Color.BLACK)){
			return true;
		}
		if ((game.getCount(Location.R_BAR) != 0 && game.getPlayerInTurn() == Color.RED)){
			return true;
		}
		
		return false;
	}
	
	private boolean movesToBar(Location to){
		if(!checkerInBar() || (checkerInBar() && toInnerTable(to)) && !(moveToPointWith1(to))){
			return true;
		}
		return false;
	}

	private boolean toInnerTable(Location to){
		
		for (int i = 0; i < 6; i++){

		if (game.getPlayerInTurn()==Color.RED && BoardImpl.BLACK_TABLE[i] == to){
			return true;
		}
		}
		
		for (int i = 0; i < 6; i++){
		if(game.getPlayerInTurn()==Color.BLACK && BoardImpl.RED_TABLE[i] == to){
			return true;
		}
	}
		
		return false;
		
	}
	
	@Override
	public boolean isValid(Location from, Location to) {
		return (moveToLegal(to)) && legalDirectionMove(from, to) && (movesToBar(to)) && (moveDirectToBarIsIllegal(to)) && distanceIsDieValue(from, to);
				
	}
}
