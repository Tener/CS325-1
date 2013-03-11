package frs.hotgammon.variants.rolldeterminers;

import java.util.Random;

import frs.hotgammon.Game;
import frs.hotgammon.RollDeterminer;
import frs.hotgammon.common.GameImpl;

public class RandomDiceRollDeterminer implements RollDeterminer {

	
	Game game;
	
	@Override
	public int[] diceThrown(GameImpl game) {
		
		int[] arr = new int[2];
		Random rand = new Random();

		arr[0] = rand.nextInt(7);
		arr[1] = rand.nextInt(7);
		
		game.currentDice = arr;
		
		return game.currentDice;
		
	}
	
	@Override
	public void setGame(Game game) {
		this.game = game;
		
	}

}
