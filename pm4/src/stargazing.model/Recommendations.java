package stargaing.model;
public class Recommendations {
  protected int recommendationId;
  protected int placeId;
  protected int userId;

  public Recommendations(int placeId, int userId) {
    this.placeId = placeId;
    this.userId = userId;
  }

  public Recommendations(int recommendationId, int placeId, int userId) {
    this.recommendationId = recommendationId;
    this.placeId = placeId;
    this.userId = userId;
  }

  public int getRecommendationId() {
    return recommendationId;
  }

  public void setRecommendationId(int recommendationId) {
    this.recommendationId = recommendationId;
  }

  public int getPlaceId() {
    return placeId;
  }

  public void setPlaceId(int placeId) {
    this.placeId = placeId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }
}
