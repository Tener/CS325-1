package frs.hotgammon;

public interface TurnChangeValidator {

	public Color nextTurnChangePlayer(Color colorInTurn);
	public void setGame(Game game);
}
