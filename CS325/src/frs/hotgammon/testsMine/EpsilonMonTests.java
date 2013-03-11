package frs.hotgammon.testsMine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import frs.hotgammon.common.GameImpl;
import frs.hotgammon.variants.factory.DeltaFactory;
import frs.hotgammon.variants.factory.EpsilonFactory;

public class EpsilonMonTests {
	
	GameImpl game;

	@Before
	public void setup(){
		game=new GameImpl(new EpsilonFactory());
		game.newGame();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
