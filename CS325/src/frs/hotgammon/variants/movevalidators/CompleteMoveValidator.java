package frs.hotgammon.variants.movevalidators;

import java.util.ArrayList;
import java.util.List;

import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.GameObserver;
import frs.hotgammon.framework.Location;
import frs.hotgammon.MoveValidator;
import frs.hotgammon.common.BoardImpl;
import frs.hotgammon.common.GameImpl;

public class CompleteMoveValidator implements MoveValidator {
	
	private GameImpl game;

	public CompleteMoveValidator() {
	}

	@Override
	public void setGame(GameImpl game) {
		
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
	
	private boolean isMoveToBearOutIllegal (Location to){
		Location bearOffTo = (game.colorInTurn == Color.BLACK) ? Location.B_BEAR_OFF : Location.R_BEAR_OFF;
		
		Location [] outsideTable = (game.colorInTurn == Color.BLACK) ? game.board.getBlackOutTable() : game.board.getRedOutTable();
		
		if( to == bearOffTo){
			for(int i = 0; i < outsideTable.length; i++){
				if( game.getCount(outsideTable[i]) > 0){
					
					if(game.getColor(outsideTable[i]) == game.colorInTurn){
						
						for( GameObserver gameObserver : game.observers ){
							  
							  gameObserver.setStatus("You Can not move to the bear off yet. Players turn: " + game.colorInTurn + " Moves left: " + game.diceRolled );
						  }
						
						return false;
					}
				}
			}
		}
		return true;
	}

	private boolean legalDirectionMove(Location from, Location to) {

		if(game.getPlayerInTurn() == Color.RED && Location.distance(from, to) < 0){
			return true;
		}
		if(game.getPlayerInTurn() == Color.BLACK && Location.distance(from, to) > 0){
			return true;
		}
		
		for( GameObserver gameObserver : game.observers ){
			  
			  gameObserver.setStatus("Moving in wrong direction. Players turn: " + game.colorInTurn + " Moves left: " + game.diceRolled + ". Roll die if no legal move.");
		  }
		return false;

	}
	
	private boolean moveToLegal(Location to){
		
		if(checkerMovedToEmptyPoint(to) || (game.getColor(to) == game.getPlayerInTurn()) || (game.getCount(to) == 1 && (game.getColor(to) != game.getPlayerInTurn()))){
			return true;
		}
		
		for( GameObserver gameObserver : game.observers ){
			  
			  gameObserver.setStatus("This is not a legal move. Click die if legal moves left." );
		  }
		return false;
	}


	public boolean distanceIsDieValue(Location from,Location to) {
		
		List<Integer> dieValues = new ArrayList<Integer>();
		
		int[] dieValuesArray = this.game.diceValuesLeft();
		
		for (int i = 0; i < dieValuesArray.length; i++){
			
			dieValues.add(dieValuesArray[i]);
		}
		
		if ( dieValues.indexOf((game.colorInTurn == Color.RED) ? (-1*Location.distance(from, to)) : Location.distance(from, to)) < 0 ){ 
			
			for( GameObserver gameObserver : game.observers ){
				  
				  gameObserver.setStatus("Player can not move die value. Players turn: " + game.colorInTurn + " Moves left: " + game.diceRolled );
			  }
			return false;
		}
		
		return true;

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
	
	private boolean checkerAlreadyInBar(Location from){
		
		Location barLoc = (game.colorInTurn == Color.BLACK) ? Location.B_BAR : Location.R_BAR;
		
		if ((this.game.getCount(barLoc) != 0) && (from != barLoc)){
			return false;
		}
		
		return true;
	}

	
	@Override
	public boolean isValid(Location from, Location to) {
		return (moveToLegal(to)) && legalDirectionMove(from, to) && (moveDirectToBarIsIllegal(to)) && distanceIsDieValue(from, to) && checkerAlreadyInBar(from) && isMoveToBearOutIllegal(to);
				
	}
}
