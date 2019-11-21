package stargazing.model;

public class Campsites extends StarGazingPlaces {

//  protected int placeId;
  protected String name;
  protected String type;


  public Campsites(double latitude, double longitude, String state, String name,
      String type) {
    super(latitude, longitude, state);
    this.name = name;
    this.type = type;
  }

  public Campsites(int placeId, double latitude, double longitude, String state,
      String name, String type) {
    super(placeId, latitude, longitude, state);
    this.name = name;
    this.type = type;
  }

  @Override
  public int getPlaceId() {
    return placeId;
  }

  @Override
  public void setPlaceId(int placeId) {
    this.placeId = placeId;
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
