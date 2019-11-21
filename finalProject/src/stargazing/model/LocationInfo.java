package stargazing.model;
public class LocationInfo {
	protected double latitude;
	protected double longitude;
	protected int elevation;
	protected String population;
	protected String state;
	protected String county;
	protected String fips;
	
	
	public LocationInfo(double latitude, double longitude, int elevation, String population,  String state,
			String county, String fips) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
		this.population = population;
		this.state = state;
		this.county = county;
		this.fips= fips;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getFips() {
		return fips;
	}
	public void setFips(String fips) {
		this.fips = fips;
	}

}
