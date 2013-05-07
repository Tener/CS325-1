package frs.hotgammon.variants.rolldeterminers;

import java.util.Random;

import frs.hotgammon.framework.Game;
import frs.hotgammon.RollDeterminer;
import frs.hotgammon.common.GameImpl;

public class RandomDiceRollDeterminer implements RollDeterminer {

	
	Game game;
	int[] arr = new int[2];
	private final int[] DICE = {1, 2, 3, 4, 5, 6};
	
	@Override
	public int[] diceThrown(GameImpl game) {
		
		Random rand = new Random(); 
		
		arr = new int[]{DICE[rand.nextInt(DICE.length )],
				DICE[rand.nextInt(DICE.length )]}; 		

		if (arr[0] == arr[1]){
			
			arr = new int[]{arr[0], arr[1], arr[0], arr[1]};
		}
		
		return arr;
		
	}
	
	@Override
	public void setGame(Game game) {
		this.game = game;
		
	}

}
