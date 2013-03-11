package frs.hotgammon.variants.factory;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.HotgammonFactory;
import frs.hotgammon.MoveValidator;
import frs.hotgammon.RollDeterminer;
import frs.hotgammon.TurnDeterminer;
import frs.hotgammon.WinnerDeterminer;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.variants.movevalidators.SimpleMoveValidator;
import frs.hotgammon.variants.rolldeterminers.FixedDiceRollDeterminer;
import frs.hotgammon.variants.turndeterminers.AlternatingTurnDeterminer;
import frs.hotgammon.variants.winnerdeterminers.SixMoveWinnerDeterminer;

public class HandicapFactory implements HotgammonFactory {
	GameImpl game;
	
	private MoveValidator moveValidator = new SimpleMoveValidator();
	
	private WinnerDeterminer winnerDeterminer = new SixMoveWinnerDeterminer();
	
	private TurnDeterminer turnDeterminer = new AlternatingTurnDeterminer();
	
	private RollDeterminer rollDeterminer = new FixedDiceRollDeterminer();
	
	private AlphaFactory blackStrategy;
	
	private BetaFactory redStrategy;
	
	private HotgammonFactory currentStrategy;
	
	
	public HandicapFactory(AlphaFactory alpha, BetaFactory beta){
		this.blackStrategy = alpha;
		this.redStrategy = beta;
		this.currentStrategy = alpha;
		this.game = alpha.game;
	}


	@Override
	public MoveValidator getMoveValidator() {
		// TODO Auto-generated method stub
		return moveValidator;
	}

	@Override
	public WinnerDeterminer getWinnerDeterminer() {
		// TODO Auto-generated method stub
		return winnerDeterminer;
	}

	@Override
	public TurnDeterminer getTurnDeterminer() {
		// TODO Auto-generated method stub
		return turnDeterminer;
	}

	@Override
	public RollDeterminer getRollDeterminer() {
		// TODO Auto-generated method stub
		return rollDeterminer;
	}

	@Override
	public void setFactory(GameImpl gameImpl) {
		
		{

			if (gameImpl.getPlayerInTurn() == Color.BLACK) {
				
				currentStrategy = blackStrategy;
			} 
			
			else{
				
				currentStrategy = redStrategy;
			}
		}
		currentStrategy.setFactory(gameImpl);
		
	}
}