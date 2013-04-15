package frs.hotgammon;

import frs.hotgammon.framework.Color;

public interface Board {

	public boolean put(Color player, int num);
	
	
	public BoardPoint returnPoint(int num );
	
}
