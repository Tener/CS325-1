package frs.hotgammon;

import frs.hotgammon.common.GameImpl;
import frs.hotgammon.framework.Location;

public interface MoveValidator {
	
	public boolean isValid(Location from, Location to);
	public void setGame(GameImpl game);

}
