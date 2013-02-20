package frs.hotgammon;

public interface TurnDeterminer {

	public Color nextTurnChangePlayer(Color colorInTurn);
	public void setGame(Game game);
}
