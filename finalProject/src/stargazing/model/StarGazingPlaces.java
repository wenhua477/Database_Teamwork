package stargazing.model;
public class StarGazingPlaces {
  protected int placeId;
  protected double latitude;
  protected double longitude;
  protected String state;
  protected double distance;
  protected String fipId;

  public String getFipId() {
	  return fipId;
  }
  
  public void setFipId(String fipId) {
	  this.fipId = fipId;
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

  public StarGazingPlaces(double latitude, double longitude, String state, String fipId) {
    this.latitude = latitude;
    this.longitude = longitude;
    this.state = state;
    this.fipId = fipId;
  }

  public StarGazingPlaces(int placeId, double latitude, double longitude, String state,  String fipId) {
    this.placeId = placeId;
    this.latitude = latitude;
    this.longitude = longitude;
    this.state = state;
    this.fipId = fipId;
  }
  
  public StarGazingPlaces(int placeId, double latitude, double longitude, String state, String fipId, double distance) {
	    this.placeId = placeId;
	    this.latitude = latitude;
	    this.longitude = longitude;
	    this.state = state;
	    this.fipId = fipId;
	    this.distance = distance;
	  }
}
