package stargazing.model;

public class Campsites extends StarGazingPlaces {


  protected String name;
  protected String type;


  public Campsites(double latitude, double longitude, String state, String fipId, String name,
      String type) {
    super(latitude, longitude, state, fipId);
    this.name = name;
    this.type = type;
  }

  public Campsites(int placeId, double latitude, double longitude, String state, String fipId,
      String name, String type) {
    super(placeId, latitude, longitude, state, fipId);
    this.name = name;
    this.type = type;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
