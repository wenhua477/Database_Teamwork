package stargazing.model;
public class CountyInfo {
	protected String countyName;
	protected String StateName;
	protected double crimeRate;
	protected String fips;
	
	public CountyInfo(String countyName, String StateName, double crimeRate, String fips) {
		super();
		this.countyName = countyName;
		this.StateName = StateName;
		this.crimeRate = crimeRate;
		this.fips = fips;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public double getCrimeRate() {
		return crimeRate;
	}

	public void setCrimeRate(double crimeRate) {
		this.crimeRate = crimeRate;
	}



	public String getStateName() {
		return StateName;
	}

	public void setStateName(String stateName) {
		StateName = stateName;
	}

	public String getFips() {
		return fips;
	}

	public void setFips(String fips) {
		this.fips = fips;
	}

	
	
}
