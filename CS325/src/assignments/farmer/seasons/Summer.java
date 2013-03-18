package assignments.farmer.seasons;

import assignments.farmer.FarmerImpl;
import assignments.farmer.Season;

public class Summer implements Season {

	FarmerImpl farmer;
	String season = "Summer";
	
	public Summer (FarmerImpl farmer){
		this.farmer = farmer;
	}

	@Override
	public void plow() {
		farmer.plowingStrategy.plow();

	}

	@Override
	public void plant() {
		farmer.plantingStrategy.plant();
	}

	@Override
	public void weedControl() {
		farmer.weedControlingStrategy.weedControl();
	}

	@Override
	public void harvest() {
		farmer.harvestingStrategy.harvest();
	}

	@Override
	public void market() {
		farmer.marketStrategy.market();
	}
	
	public String toString() {
		return season;
		
	}

}
