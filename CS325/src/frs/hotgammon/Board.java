package frs.hotgammon;

public interface Board {

	public boolean put(Color player, int sqNumber);
	
	public boolean remove(Color player, int sqNumber);
	
	public Square getSquare(int sqNumber );
	
	
}
