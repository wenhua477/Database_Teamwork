package stargazing.model;
public class StarGazingPlaces {
  protected int placeId;
  protected double latitude;
  protected double longitude;
  protected String state;

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

  public StarGazingPlaces(double latitude, double longitude, String state) {
    this.latitude = latitude;
    this.longitude = longitude;
    this.state = state;
  }

  public StarGazingPlaces(int placeId, double latitude, double longitude, String state) {
    this.placeId = placeId;
    this.latitude = latitude;
    this.longitude = longitude;
    this.state = state;
  }
}
