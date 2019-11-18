package stargazing.model;
public class CountyInfo {
	protected String countyName;
	protected double crimeRate;
	
	public CountyInfo(String countyName, double crimeRate) {
		super();
		this.countyName = countyName;
		this.crimeRate = crimeRate;
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
	
	
}
