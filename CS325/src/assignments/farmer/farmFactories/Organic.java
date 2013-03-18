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

public class Organic implements FarmFactory {

	Season season;
	
	

	public String toString() {
		return "Organic";
	}

	@Override
	public PlowingStrategy createPlowingStrategy() {
		if( season.toString().equals("Spring") ){
			return new SpringOrganicPlow();
		}
		if( season.toString().equals("Summer") ){
			return new SummerOrganicPlow();
		}
		if( season.toString().equals("Fall") ){
			return new FallOrganicPlow();
		}
		return null;
	}

	@Override
	public PlantingStrategy createPlantingStrategy() {
		if( season.toString().equals("Spring") ){
			return new SpringOrganicPlant();
		}
		if( season.toString().equals("Summer") ){
			return new SummerOrganicPlant();
		}
		if( season.toString().equals("Fall") ){
			return new FallOrganicPlant();
		}
		return null;
	}

	@Override
	public WeedControlingStrategy createWeedControlingStrategy() {
		if( season.toString().equals("Spring") ){
			return new SpringOrganicWeeds();
		}
		if( season.toString().equals("Summer") ){
			return new SummerOrganicWeeds();
		}
		if( season.toString().equals("Fall") ){
			return new FallOrganicWeeds();
		}
		return null;
	}

	@Override
	public HarvestingStrategy createHarvestingStrategy() {
		if( season.toString().equals("Spring") ){
			return new SpringOrganicHarvest();
		}
		if( season.toString().equals("Summer") ){
			return new SummerOrganicHarvest();
		}
		if( season.toString().equals("Fall") ){
			return new FallOrganicHarvest();
		}
		return null;
	}

	@Override
	public MarketStrategy createMarketStrategy() {
		if( season.toString().equals("Spring") ){
			return new SpringOrganicMarket();
		}
		if( season.toString().equals("Summer") ){
			return new SummerOrganicMarket();
		}
		if( season.toString().equals("Fall") ){
			return new FallOrganicMarket();
		}
		return null;
	}

	@Override
	public void setSeason(Season season) {
		this.season = season;
	}

}
