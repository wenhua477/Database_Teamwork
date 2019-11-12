package model;

public class LocationInfo {
	protected double latitude;
	protected double longitude;
	protected int elevation;
	protected String population;
	protected String zip;
	protected String state;
	protected String county;
	
	public LocationInfo(double latitude, double longitude, int elevation, String population, String zip, String state,
			String county) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
		this.population = population;
		this.zip = zip;
		this.state = state;
		this.county = county;
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
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
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
	
	
}
