package frs.hotgammon;

public interface WinnerDeterminer {

	public Color returnWinner();
	public void setGame(Game game);
	public void makeATurn();
}
