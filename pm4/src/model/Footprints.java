package model;

import java.util.Date;

public class Footprints {
  protected int footprintId;
  protected int userId;
  protected int placeId;
  protected Date timeVisited;

  public Footprints(int userId, int placeId, Date timeVisited) {
    this.userId = userId;
    this.placeId = placeId;
    this.timeVisited = timeVisited;
  }

  public Footprints(int footprintId, int userId, int placeId, Date timeVisited) {
    this.footprintId = footprintId;
    this.userId = userId;
    this.placeId = placeId;
    this.timeVisited = timeVisited;
  }

  public int getFootprintId() {
    return footprintId;
  }

  public void setFootprintId(int footprintId) {
    this.footprintId = footprintId;
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

  public Date getTimeVisited() {
    return timeVisited;
  }

  public void setTimeVisited(Date timeVisited) {
    this.timeVisited = timeVisited;
  }
}
