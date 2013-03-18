package assignments.farmer.farmFactories;

import assignments.farmer.FarmFactory;
import assignments.farmer.HarvestingStrategy;
import assignments.farmer.MarketStrategy;
import assignments.farmer.PlantingStrategy;
import assignments.farmer.PlowingStrategy;
import assignments.farmer.Season;
import assignments.farmer.WeedControlingStrategy;
import assignments.farmer.strategies.harvesting.FallAmishHarvest;
import assignments.farmer.strategies.harvesting.SpringAmishHarvest;
import assignments.farmer.strategies.harvesting.SummerAmishHarvest;
import assignments.farmer.strategies.market.FallAmishMarket;
import assignments.farmer.strategies.market.SpringAmishMarket;
import assignments.farmer.strategies.market.SummerAmishMarket;
import assignments.farmer.strategies.planting.FallAmishPlant;
import assignments.farmer.strategies.planting.SpringAmishPlant;
import assignments.farmer.strategies.planting.SummerAmishPlant;
import assignments.farmer.strategies.plowing.FallAmishPlow;
import assignments.farmer.strategies.plowing.SpringAmishPlow;
import assignments.farmer.strategies.plowing.SummerAmishPlow;
import assignments.farmer.strategies.weedControling.FallAmishWeeds;
import assignments.farmer.strategies.weedControling.SpringAmishWeeds;
import assignments.farmer.strategies.weedControling.SummerAmishWeeds;

public class Amish implements FarmFactory {
	
	Season season;
	
	

	public String toString() {
		return "Amish";
	}

	@Override
	public PlowingStrategy createPlowingStrategy() {
		if( season.toString().equals("Spring") ){
			return new SpringAmishPlow();
		}
		if( season.toString().equals("Summer") ){
			return new SummerAmishPlow();
		}
		if( season.toString().equals("Fall") ){
			return new FallAmishPlow();
		}
		return null;
	}

	@Override
	public PlantingStrategy createPlantingStrategy() {
		if( season.toString().equals("Spring") ){
			return new SpringAmishPlant();
		}
		if( season.toString().equals("Summer") ){
			return new SummerAmishPlant();
		}
		if( season.toString().equals("Fall") ){
			return new FallAmishPlant();
		}
		return null;
	}

	@Override
	public WeedControlingStrategy createWeedControlingStrategy() {
		if( season.toString().equals("Spring") ){
			return new SpringAmishWeeds();
		}
		if( season.toString().equals("Summer") ){
			return new SummerAmishWeeds();
		}
		if( season.toString().equals("Fall") ){
			return new FallAmishWeeds();
		}
		return null;
	}

	@Override
	public HarvestingStrategy createHarvestingStrategy() {
		if( season.toString().equals("Spring") ){
			return new SpringAmishHarvest();
		}
		if( season.toString().equals("Summer") ){
			return new SummerAmishHarvest();
		}
		if( season.toString().equals("Fall") ){
			return new FallAmishHarvest();
		}
		return null;
	}

	@Override
	public MarketStrategy createMarketStrategy() {
		if( season.toString().equals("Spring") ){
			return new SpringAmishMarket();
		}
		if( season.toString().equals("Summer") ){
			return new SummerAmishMarket();
		}
		if( season.toString().equals("Fall") ){
			return new FallAmishMarket();
		}
		return null;
	}

	@Override
	public void setSeason(Season season) {
		this.season = season;
	}

}
