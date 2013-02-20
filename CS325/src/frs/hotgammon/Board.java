package frs.hotgammon;

public interface Board {

	public boolean put(Color player, int num);
	
	
	public BoardPoint returnPoint(int num );
	
}
