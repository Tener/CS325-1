package frs.hotgammon.variants;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.Location;
import frs.hotgammon.MoveValidator;
import frs.hotgammon.common.BoardImpl;

public class BetaMoveValidator implements MoveValidator {
	
	private Game game;

	public BetaMoveValidator() {

	}

	@Override
	public void setGame(Game game) {
		
		this.game = game;
	}
	
	private boolean checkerToEmptyPoint(Location to){
		if( game.getColor(to) == Color.NONE && game.getCount(to) == 0){
			return true;
		}
		
		return false;
	}

	private boolean sameColorPoint(Location to) {
		if(game.getColor(to) == game.getPlayerInTurn()){
			return true;
		}
		return false;
	}
	private boolean legalRedDirection(Location from, Location to) {
		
		if(game.getPlayerInTurn() == Color.RED && Location.distance(from, to) < 0){
			return true;
		}
		return false;
	}

	private boolean legalBlackDirection(Location from, Location to) {
		
		if(game.getPlayerInTurn() == Color.BLACK && Location.distance(from, to) > 0){
			return true;
		}
		return false;
	}

	private boolean legalDirectionMove(Location from, Location to) {

		if (legalRedDirection(from, to) || legalBlackDirection(
				from, to)){
			return true;
		}
		return false;

	}
	
	private boolean moveToLegal(Location to){
		
		if(checkerToEmptyPoint(to) || sameColorPoint(to) || moveToPointWith1(to)){
			return true;
		}
		return false;
	}


	public boolean distanceTravelledEqualsTheValueOfDieRolled(Location from,
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

	private boolean blackCheckerInBar() {
		if (((BoardImpl) game.playingBoard()).getSquare(Location.B_BAR.ordinal()).pieces != 0 && game.getPlayerInTurn() == Color.BLACK){
			return true;
		}
		return false;
	}

	private boolean redCheckerInBar() {
		
		if (((BoardImpl) game.playingBoard()).getSquare(Location.R_BAR.ordinal()).pieces != 0 && game.getPlayerInTurn() == Color.RED){
			return true;
		}
		return false;
	}

	private boolean checkerInBar() {
		
		if( blackCheckerInBar()){
			return true;
		}
		if( redCheckerInBar()){
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

	private boolean moveToInnerTableBlack(Location to){

		if(game.getPlayerInTurn()==Color.BLACK && BoardImpl.redInnerTable.contains(to)){
			return true;
		}
		return false;
	}

	private boolean moveToInnerTableRed(Location to){

		if (game.getPlayerInTurn()==Color.RED && BoardImpl.blackInnerTable.contains(to)){
			return true;
		}
		return false;
	}

	private boolean toInnerTable(Location to){

		if (moveToInnerTableBlack(to) || moveToInnerTableRed(to)){
			return true;
		}
		return false;
		
	}
	
	@Override
	public boolean isValid(Location from, Location to) {

		return (moveToLegal(to)) && legalDirectionMove(from, to) && (movesToBar(to)) && (moveDirectToBarIsIllegal(to)) && distanceTravelledEqualsTheValueOfDieRolled(from, to);
				
	}
}
