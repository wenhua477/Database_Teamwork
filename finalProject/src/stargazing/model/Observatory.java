package stargazing.model;
public class Observatory extends StarGazingPlaces {

  protected double price;
  protected String openHour;

  public Observatory(double latitude, double longitude, String state, String fips, double price,
      String openHour) {
    super(latitude, longitude, state, fips);
    this.price = price;
    this.openHour = openHour;
  }

  public Observatory(int placeId, double latitude, double longitude, String state, String fips, double price,
      String openHour) {
    super(placeId, latitude, longitude, state, fips);
    this.price = price;
    this.openHour = openHour;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getOpenHour() {
    return openHour;
  }

  public void setOpenHour(String openHour) {
    this.openHour = openHour;
  }
}
