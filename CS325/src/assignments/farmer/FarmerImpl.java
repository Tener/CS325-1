package assignments.farmer;

import assignments.farmer.farmFactories.Amish;
import assignments.farmer.farmFactories.Conventional;
import assignments.farmer.farmFactories.Organic;
import assignments.farmer.seasons.Fall;
import assignments.farmer.seasons.Spring;
import assignments.farmer.seasons.Summer;

public class FarmerImpl implements Farmer {
	
	public FarmFactory farmFactory;

	public PlowingStrategy plowingStrategy;
	public PlantingStrategy plantingStrategy;
	public WeedControlingStrategy weedControlingStrategy;
	public HarvestingStrategy harvestingStrategy;
	public MarketStrategy marketStrategy;
	public Season season;
	
	public FarmerImpl( FarmFactory farmFactory){
		this.season = new Spring(this);
		
		farmFactory.setSeason(season);
		
		this.farmFactory = farmFactory;
		
		setStrategies();
		
	}

	@Override
	public void plow() {
		season.plow();
	}

	@Override
	public void plant() {
		season.plant();
	}

	@Override
	public void weedControl() {
		season.weedControl();
	}

	@Override
	public void harvest() {
		season.harvest();
	}

	@Override
	public void market() {
		season.market();
	}
	
	public void seasonChange(){
		if( season.toString().equals("Spring")){
			this.season = new Summer(this);
		}
		else if(season.toString().equals("Summer")){
			this.season = new Fall(this);
		}
		else if(season.toString().equals("Fall")){
			this.season = new Spring(this);
		}
		
		farmFactory.setSeason(season);
		setStrategies();
	}

	private void setStrategies() {
		
		plowingStrategy = farmFactory.createPlowingStrategy();
		plantingStrategy = farmFactory.createPlantingStrategy();
		weedControlingStrategy = farmFactory.createWeedControlingStrategy();
		harvestingStrategy = farmFactory.createHarvestingStrategy();
		marketStrategy = farmFactory.createMarketStrategy();
		
	}
	
	
	public static void main( String[] args){
		
		FarmerImpl farmerImpl = new FarmerImpl(new Amish());
		
		System.out.println("******************** AMISH *******************");

		for(int i = 0; i < 7; i++){
			
			System.out.println("Season iteration " + i + " in season " + farmerImpl.season.toString());
			
			farmerImpl.plow();
			farmerImpl.plant();
			farmerImpl.weedControl();
			farmerImpl.harvest();
			farmerImpl.market();
			
			System.out.println();

			farmerImpl.seasonChange();
			
		}
		
		FarmerImpl farmerImpl1 = new FarmerImpl(new Conventional());
		
		System.out.println("******************** CONVENTIONAL *******************");

		for(int i = 0; i < 7; i++){
			
			System.out.println("Season iteration " + i + " in season " + farmerImpl1.season.toString());
			
			farmerImpl1.plow();
			farmerImpl1.plant();
			farmerImpl1.weedControl();
			farmerImpl1.harvest();
			farmerImpl1.market();
			
			System.out.println();

			farmerImpl1.seasonChange();
			
		}
		
		System.out.println("******************** ORGANIC *******************");
		
		FarmerImpl farmerImpl2 = new FarmerImpl(new Organic());
		
		for(int i = 0; i < 7; i++){
			
			System.out.println("Season iteration " + i + " in season " + farmerImpl2.season.toString());
			
			farmerImpl2.plow();
			farmerImpl2.plant();
			farmerImpl2.weedControl();
			farmerImpl2.harvest();
			farmerImpl2.market();
			
			System.out.println();

			farmerImpl2.seasonChange();
			
		}
		
	}

}
