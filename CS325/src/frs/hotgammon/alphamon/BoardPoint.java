package frs.hotgammon.alphamon;

public class BoardPoint {
	
	public BoardPoint(Location name, Color color, int numCheckers){
		this.setLocationName(name);
		this.setColorOfLocation(color);
		this.setNumberOfCheckers(numCheckers);
	}
	
	public BoardPoint() {
	}

	public Location locationName = null;
	
	public Color colorOfLocation = Color.NONE;
	
	public int numberOfCheckers = 0;

	public Location getLocationName() {
		return locationName;
	}

	public void setLocationName(Location locationName) {
		this.locationName = locationName;
	}

	public Color getColorOfLocation() {
		return colorOfLocation;
	}

	public void setColorOfLocation(Color colorOfLocation) {
		this.colorOfLocation = colorOfLocation;
	}

	public int getNumberOfCheckers() {
		return numberOfCheckers;
	}

	public void setNumberOfCheckers(int numberOfCheckers) {
		this.numberOfCheckers = numberOfCheckers;
	}

}
