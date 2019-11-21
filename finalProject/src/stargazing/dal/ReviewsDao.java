package stargazing.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import stargazing.model.Reviews;
import stargazing.model.StarGazingPlaces;

public class ReviewsDao {

  protected ConnectionManager connectionManager;

  private static ReviewsDao instance = null;

  protected ReviewsDao() {
    connectionManager = new ConnectionManager();
  }

  public static ReviewsDao getInstance() {
    if (instance == null) {
      instance = new ReviewsDao();
    }
    return instance;
  }

  public Reviews create(Reviews review) throws SQLException {
    String insertReviews =
        "INSERT INTO Reviews(CreatedTime, Content, Rating, UserId, PlaceId) " +
            " VALUES(?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertReviews);
      insertStmt.setTimestamp(1, new java.sql.Timestamp(review.getCreatedTime().getTime()));
      insertStmt.setString(2, review.getContent());
      insertStmt.setDouble(3, review.getRating());
      insertStmt.setInt(4, review.getUserId());
      insertStmt.setInt(5, review.getPlaceId());

      insertStmt.executeUpdate();
      return review;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (insertStmt != null) {
        insertStmt.close();
      }
    }
  }
  
  public List<StarGazingPlaces> getTop10Rated()throws SQLException{
	  String selectPlaceIds =
		        "SELECT PlaceId, AVG(Rating) AS CNT "
		            + " FROM Reviews "
		            + " GROUP BY PlaceId"
		            + " ORDER BY CNT DESC " 
		            + " LIMIT 10;";
		    List<StarGazingPlaces> starGazingPlacesList = new ArrayList<StarGazingPlaces>();
		    Connection connection = null;
		    PreparedStatement selectStmt = null;
		    ResultSet results = null;
		    try {
		      connection = connectionManager.getConnection();
		      selectStmt = connection.prepareStatement(selectPlaceIds);
		      results = selectStmt.executeQuery();

		      while (results.next()) {

		        int placeId = results.getInt("PlaceId");
		
		        CampsitesDao campsiteDao = new CampsitesDao();
		        ObservatoryDao observatoryDao = new ObservatoryDao();
		        StarGazingPlaces place1 = campsiteDao.getCampsitesById(placeId);
		        StarGazingPlaces place2 = observatoryDao.getObservatoryById(placeId);
		        if (place1 != null) {
		        	starGazingPlacesList.add(place1);
		        } else if (place2 != null){
		        	starGazingPlacesList.add(place2);
		        }
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		      throw e;
		    } finally {
		      if (connection != null) {
		        connection.close();
		      }
		      if (selectStmt != null) {
		        selectStmt.close();
		      }
		      if (results != null) {
		        results.close();
		      }
		    }
		    return starGazingPlacesList;
  }

  public Reviews updateContent(Reviews review, String newContent) throws SQLException {
    String updateReviews = "UPDATE Reviews SET Content=?,Created=? WHERE ReviewId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateReviews);

      updateStmt.setString(1, newContent);
      Date newCreatedTimestamp = new Date();
      updateStmt.setTimestamp(2, new Timestamp(newCreatedTimestamp.getTime()));
      updateStmt.setInt(3, review.getReviewId());
      updateStmt.executeUpdate();

      // Update the blogComment param before returning to the caller.
      review.setContent(newContent);
      review.setCreatedTime(newCreatedTimestamp);
      return review;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (updateStmt != null) {
        updateStmt.close();
      }
    }
  }

  public Reviews getReviewById(int reviewId) throws SQLException {
    String selectReview =
        "SELECT ReviewId,CreatedTime, Content, Rating, UserId, PlaceId " +
            " FROM Reviews WHERE ReviewId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectReview);
      selectStmt.setInt(1, reviewId);
      results = selectStmt.executeQuery();
      if (results.next()) {

        String content = results.getString("Content");
        double rating = results.getDouble("Rating");
        int userId = results.getInt("UserId");
        int placeId = results.getInt("PlaceId");
        Date createTime = new Date(results.getTimestamp("CreatedTime").getTime());

        Reviews review = new Reviews(reviewId, createTime, content, rating, userId, placeId);
        return review;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
      if (results != null) {
        results.close();
      }
    }
    return null;
  }

  public List<Reviews> getReviewsByPlaceId(int placeId) throws SQLException {
    List<Reviews> reviews = new ArrayList<Reviews>();
    String selectReviews =
        "SELECT ReviewId,CreatedTime, Content, Rating, UserId,Reviews.PlaceId as PlaceId "
            +
            " FROM Reviews INNER JOIN StarGazingPlaces " +
            " ON Reviews.PlaceId = StarGazingPlaces.PlaceId " +
            " WHERE Reviews.PlaceId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectReviews);
      selectStmt.setInt(1, placeId);
      results = selectStmt.executeQuery();
      while (results.next()) {
        int reviewId = results.getInt("ReviewId");
        String content = results.getString("Content");
        Date created = new Date(results.getTimestamp("CreatedTime").getTime());
        double rating = results.getDouble("Rating");
        int userId = results.getInt("UserId");

        Reviews review = new Reviews(reviewId, created, content, rating, userId, placeId);
        reviews.add(review);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
      if (results != null) {
        results.close();
      }
    }
    return reviews;
  }

  public List<Reviews> getReviewsByUserId(int userId) throws SQLException {
    List<Reviews> reviews = new ArrayList<Reviews>();
    String selectReviews =
        "SELECT ReviewId,CreatedTime, Content, Rating, PlaceId,Reviews.UserId as UserId "
            +
            " FROM Reviews INNER JOIN Users " +
            " ON Reviews.UserId = Users.UserId " +
            " WHERE Reviews.UserId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectReviews);
      selectStmt.setInt(1, userId);
      results = selectStmt.executeQuery();
      while (results.next()) {
        int reviewId = results.getInt("ReviewId");
        String content = results.getString("Content");
        Date created = new Date(results.getTimestamp("CreatedTime").getTime());
        double rating = results.getDouble("Rating");
        int placeId = results.getInt("PlaceId");

        Reviews review = new Reviews(reviewId, created, content, rating, userId, placeId);
        reviews.add(review);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
      if (results != null) {
        results.close();
      }
    }
    return reviews;
  }

  public Reviews delete(Reviews review) throws SQLException {
    String deleteReview = "DELETE FROM Reviews WHERE ReviewId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteReview);
      deleteStmt.setInt(1, review.getReviewId());
      deleteStmt.executeUpdate();

      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }
}
