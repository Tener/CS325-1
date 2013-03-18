package assignments.farmer.strategies.plowing;

import assignments.farmer.PlowingStrategy;

public class SpringConventionalPlow implements PlowingStrategy {

	@Override
	public void plow() {
		System.out.println("Using no-till, No Plowing.");
	}

}
