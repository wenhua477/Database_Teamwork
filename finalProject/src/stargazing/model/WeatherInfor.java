package stargazing.model;

import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.demo.Main;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.CurrentWeather;

public class WeatherInfor {
    protected String cityName;
    protected String minTemp;
    protected String maxTemp;
    
    protected String Temperature;
    protected Boolean hasRain;
    
	


	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
		
	}

	public String getTemperature() {
		return Temperature;
	}

	public void setTemperature(String temperature) {
		
		Temperature = temperature;
	}

	public Boolean getHasRain() {
		return hasRain;
	}

	public void setHasRain(Boolean hasRain) {
		this.hasRain = hasRain;
	}

	public String getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(Double minTemp) {
		
		String y = String.valueOf(String.format("%.2f", minTemp)) + "°C";
		this.minTemp = y;
	}

	public String getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(Double maxTemp) {
		String y = String.valueOf(String.format("%.2f", maxTemp)) + "°C";
		this.maxTemp = y;
	}
  
	  
}
