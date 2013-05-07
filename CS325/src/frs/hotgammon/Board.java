package frs.hotgammon;

import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Location;

public interface Board {

	public boolean put(Color player, int num);
	
	
	public BoardPoint returnPoint(int num );


	public boolean move(Location from, Location to, Color checkerColor);


	public boolean place(Color opponentColor, int ordinal);


	public boolean remove(Color opponentColor, int ordinal);
	
	public Location[] getBlackTable();
	
	public Location[] getRedTable();
	
	public Location[] getBlackOutTable();
	
	public Location[] getRedOutTable();
	
	public BoardPoint[] getBoard();
	
}
