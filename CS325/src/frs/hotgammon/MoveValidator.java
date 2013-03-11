package frs.hotgammon;

import frs.hotgammon.common.GameImpl;

public interface MoveValidator {
	
	public boolean isValid(Location from, Location to);
	public void setGame(GameImpl game);

}
