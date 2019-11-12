package stargaing.model;
import java.util.Date;
import javax.swing.text.AbstractDocument.Content;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

public class Reviews {
  protected  int reviewId;
  protected Date createdTime;
  protected String content;
  protected double rating;
  protected int userId;
  protected int placeId;

  public Reviews(int reviewId, Date createdTime, String content, double rating, int userId,
      int placeId) {
    this.reviewId = reviewId;
    this.createdTime = createdTime;
    this.content = content;
    this.rating = rating;
    this.userId = userId;
    this.placeId = placeId;
  }

  public Reviews(Date createdTime, String content, double rating, int userId, int placeId) {
    this.createdTime = createdTime;
    this.content = content;
    this.rating = rating;
    this.userId = userId;
    this.placeId = placeId;
  }

  public int getReviewId() {
    return reviewId;
  }

  public void setReviewId(int reviewId) {
    this.reviewId = reviewId;
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getPlaceId() {
    return placeId;
  }

  public void setPlaceId(int placeId) {
    this.placeId = placeId;
  }
}
