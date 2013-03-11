package frs.hotgammon;

import frs.hotgammon.common.GameImpl;

public interface HotgammonFactory {
	
	public MoveValidator getMoveValidator();
	
	public WinnerDeterminer getWinnerDeterminer();
	
	public TurnDeterminer getTurnDeterminer();
	
	public RollDeterminer getRollDeterminer();

	public void setFactory(GameImpl gameImpl);

}
