package stargazing.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import stargazing.model.Recommendations;
import stargazing.model.StarGazingPlaces;

public class RecommendationsDao {

  protected ConnectionManager connectionManager;

  private static RecommendationsDao instance = null;

  protected RecommendationsDao() {
    connectionManager = new ConnectionManager();
  }

  public static RecommendationsDao getInstance() {
    if (instance == null) {
      instance = new RecommendationsDao();
    }
    return instance;
  }

  public Recommendations create(Recommendations recommendation) throws SQLException {
    String insertRecommendations =
        "INSERT INTO Recommendations(UserId, PlaceId) " +
            " VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertRecommendations);
      insertStmt.setInt(1, recommendation.getUserId());
      insertStmt.setInt(2, recommendation.getPlaceId());

      insertStmt.executeUpdate();
      return recommendation;
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
  
  public List<StarGazingPlaces> getTop10Recommended()throws SQLException{
	  String selectPlaceIds =
		        "SELECT PlaceId, COUNT(*) AS CNT "
		            + " FROM Recommendations "
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
  

  public Recommendations getRecommendationById(int recommendationId) throws SQLException {
    String selectRecommendation =
        "SELECT RecommendationId,UserId, PlaceId " +
            " FROM Recommendations WHERE RecommendationId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectRecommendation);
      selectStmt.setInt(1, recommendationId);
      results = selectStmt.executeQuery();
      if (results.next()) {
        int userId = results.getInt("UserId");
        int placeId = results.getInt("PlaceId");

        Recommendations recommendation = new Recommendations(recommendationId, placeId, userId);
        return recommendation;
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


  public List<Recommendations> getRecommendationsByUserId(int userId) throws SQLException {
    List<Recommendations> recommendations = new ArrayList<Recommendations>();
    String selectRecommendations =
        "SELECT RecommendationId,UserId, PlaceId "
            +
            " FROM Recommendations " +
            " WHERE UserId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectRecommendations);
      selectStmt.setInt(1, userId);
      results = selectStmt.executeQuery();
      while (results.next()) {
        int placeId = results.getInt("PlaceId");
        int recommendationId = results.getInt("RecommendationId");
        Recommendations recommendation = new Recommendations(recommendationId, placeId, userId);
        recommendations.add(recommendation);
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
    return recommendations;
  }

  public List<Recommendations> getRecommendationsByPlaceId(int placeId) throws SQLException {
    List<Recommendations> recommendations = new ArrayList<Recommendations>();
    String selectRecommendations =
        "SELECT RecommendationId,UserId, PlaceId "
            +
            " FROM Recommendations " +
            " WHERE PlaceId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectRecommendations);
      selectStmt.setInt(1, placeId);
      results = selectStmt.executeQuery();
      while (results.next()) {
        int userId = results.getInt("UserId");
        int recommendationId = results.getInt("RecommendationId");
        Recommendations recommendation = new Recommendations(recommendationId, placeId, userId);
        recommendations.add(recommendation);
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
    return recommendations;
  }

  public Recommendations delete(Recommendations recommendation)
      throws SQLException {
    String deleteRecommendation = "DELETE FROM Recommendations WHERE RecommendationId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteRecommendation);
      deleteStmt.setInt(1, recommendation.getRecommendationId());
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
