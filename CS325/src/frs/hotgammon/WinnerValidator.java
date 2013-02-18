package frs.hotgammon;

public interface WinnerValidator {

	public Color returnWinner();
	public void setGame(Game game);
	public void makeATurn();
}
