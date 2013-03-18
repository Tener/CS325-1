package assignments.farmer;

public interface FarmFactory {
	
	public PlowingStrategy createPlowingStrategy();
	public PlantingStrategy createPlantingStrategy();
	public WeedControlingStrategy createWeedControlingStrategy();
	public HarvestingStrategy createHarvestingStrategy();
	public MarketStrategy createMarketStrategy();
	public void setSeason(Season season);

	public String toString();

}
