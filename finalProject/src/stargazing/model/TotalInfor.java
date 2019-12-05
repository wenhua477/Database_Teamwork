package stargazing.model;

public class TotalInfor {
  protected int placeId;
  protected double latitude;
  protected double longitude;
  protected String state;
  protected double distance;
  protected String fips;
  
//  protected String name;
//  protected String type;
  
	protected int elevation;
	protected String population;
	
	protected double crimeRate;
	
	public TotalInfor(int placeId, double latitude, double longitude, String state, double distance, String fips,
			 int elevation, String population) {
//		super();
		this.placeId = placeId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.state = state;
		this.distance = distance;
		this.fips = fips;
		this.elevation = elevation;
		this.population = population;
	}

	
	

	public TotalInfor(int placeId, double latitude, double longitude, String state, double distance, String fips,
			 int elevation, String population, double crimeRate) {
//		super();
		this.placeId = placeId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.state = state;
		this.distance = distance;
		this.fips = fips;
		this.elevation = elevation;
		this.population = population;
		this.crimeRate = crimeRate;
	}

	public int getPlaceId() {
		return placeId;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getFips() {
		return fips;
	}

	public void setFips(String fips) {
		this.fips = fips;
	}


	public int getElevation() {
		return elevation;
	}

	public void setElevation(int elevation) {
		this.elevation = elevation;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public double getCrimeRate() {
		return crimeRate;
	}

	public void setCrimeRate(double crimeRate) {
		this.crimeRate = crimeRate;
	}
	
	
	
	
}
