package assignments.farmer.farmFactories;

import assignments.farmer.FarmFactory;
import assignments.farmer.HarvestingStrategy;
import assignments.farmer.MarketStrategy;
import assignments.farmer.PlantingStrategy;
import assignments.farmer.PlowingStrategy;
import assignments.farmer.Season;
import assignments.farmer.WeedControlingStrategy;
import assignments.farmer.strategies.harvesting.*;
import assignments.farmer.strategies.market.*;
import assignments.farmer.strategies.planting.*;
import assignments.farmer.strategies.plowing.*;
import assignments.farmer.strategies.weedControling.*;

public class Conventional implements FarmFactory {

	Season season;
	
	

	public String toString() {
		return "Conventional";
	}

	@Override
	public PlowingStrategy createPlowingStrategy() {
		if( season.toString().equals("Spring") ){
			return new SpringConventionalPlow();
		}
		if( season.toString().equals("Summer") ){
			return new SummerConventionalPlow();
		}
		if( season.toString().equals("Fall") ){
			return new FallConventionalPlow();
		}
		return null;
	}

	@Override
	public PlantingStrategy createPlantingStrategy() {
		if( season.toString().equals("Spring") ){
			return new SpringConventionalPlant();
		}
		if( season.toString().equals("Summer") ){
			return new SummerConventionalPlant();
		}
		if( season.toString().equals("Fall") ){
			return new FallConventionalPlant();
		}
		return null;
	}

	@Override
	public WeedControlingStrategy createWeedControlingStrategy() {
		if( season.toString().equals("Spring") ){
			return new SpringConventionalWeeds();
		}
		if( season.toString().equals("Summer") ){
			return new SummerConventionalWeeds();
		}
		if( season.toString().equals("Fall") ){
			return new FallConventionalWeeds();
		}
		return null;
	}

	@Override
	public HarvestingStrategy createHarvestingStrategy() {
		if( season.toString().equals("Spring") ){
			return new SpringConventionalHarvest();
		}
		if( season.toString().equals("Summer") ){
			return new SummerConventionalHarvest();
		}
		if( season.toString().equals("Fall") ){
			return new FallConventionalHarvest();
		}
		return null;
	}

	@Override
	public MarketStrategy createMarketStrategy() {
		if( season.toString().equals("Spring") ){
			return new SpringConventionalMarket();
		}
		if( season.toString().equals("Summer") ){
			return new SummerConventionalMarket();
		}
		if( season.toString().equals("Fall") ){
			return new FallConventionalMarket();
		}
		return null;
	}

	@Override
	public void setSeason(Season season) {
		this.season = season;
	}

}
