package stargazing.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import stargazing.model.StarGazingPlaces;

public class StarGazingPlacesDao {

  protected ConnectionManager connectionManager;

  private static StarGazingPlacesDao instance = null;

  protected StarGazingPlacesDao() {
    connectionManager = new ConnectionManager();
  }

  public static StarGazingPlacesDao getInstance() {
    if (instance == null) {
      instance = new StarGazingPlacesDao();
    }
    return instance;
  }


  public StarGazingPlaces create(StarGazingPlaces starGazingPlace) throws SQLException {
    String insertStarGazingPlaces =
        "INSERT INTO StarGazingPlaces(PlaceId, Latitude, Longitude, State) " +
            " VALUES(?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertStarGazingPlaces);

      insertStmt.setInt(1, starGazingPlace.getPlaceId());
      insertStmt.setDouble(2, starGazingPlace.getLatitude());
      insertStmt.setDouble(3, starGazingPlace.getLongitude());
      insertStmt.setString(4, starGazingPlace.getState());

      insertStmt.executeUpdate();
      return starGazingPlace;
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

  public StarGazingPlaces getStarGazingPlacesById(int starGazingPlacesId) throws SQLException {
    String selectStarGazingPlaces =
        "SELECT PlaceId, Latitude, Longitude, State " +
            " FROM StarGazingPlaces WHERE PlacesId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectStarGazingPlaces);
      selectStmt.setInt(1, starGazingPlacesId);
      results = selectStmt.executeQuery();
      if (results.next()) {

        int placeId = results.getInt("PlaceId");
        double latitude = results.getDouble("Latitude");
        double longitude = results.getDouble("Longitude");
        String state = results.getString("State");
        StarGazingPlaces starGazingPlace = new StarGazingPlaces(placeId, latitude, longitude,
            state);
        return starGazingPlace;
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

  public List<StarGazingPlaces> getStargazingPlacesByLatitudeAndLongitude(double latitude,
      double longitude) throws SQLException {
    //    1 latitude = 111km.
    String selectStarGazingPlaces =
        "SELECT PlaceId, Latitude, Longitude, State, SQRT(POW(Latitude - ?, 2) + POW(Longitude - ?, 2)) AS Total_dist "
            + " FROM StarGazingPlaces "
            + " HAVING Total_dist < 50 "
            + " ORDER BY Total_dist ASC;";
    List<StarGazingPlaces> starGazingPlacesList = new ArrayList<StarGazingPlaces>();
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectStarGazingPlaces);
      selectStmt.setDouble(1, latitude);
      selectStmt.setDouble(2, longitude);
      results = selectStmt.executeQuery();

      while (results.next()) {

        int placeId = results.getInt("PlaceId");
        double resultLatitude = results.getDouble("Latitude");
        double resultLongitude = results.getDouble("Longitude");
        String state = results.getString("State");
        double resultDistance = results.getDouble("Total_dist");

        StarGazingPlaces starGazingPlace = new StarGazingPlaces(placeId, resultLatitude, resultLongitude,
            state, resultDistance);
        starGazingPlacesList.add(starGazingPlace);
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


  public List<StarGazingPlaces> getStargazingPlacesByLatitudeAndLongitude(double latitude,
      double longitude, double distanceLimit) throws SQLException {
    //    1 latitude = 111km.
    String selectStarGazingPlaces =
        "SELECT PlaceId, Latitude, Longitude, State, SQRT(POW(Latitude - ?, 2) + POW(Longitude - ?, 2)) AS Total_dist "
            + " FROM StarGazingPlaces "
            + " HAVING Total_dist < ? "
            + " ORDER BY Total_dist ASC;";
    List<StarGazingPlaces> starGazingPlacesList = new ArrayList<StarGazingPlaces>();
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectStarGazingPlaces);
      selectStmt.setDouble(1, latitude);
      selectStmt.setDouble(2, longitude);
      selectStmt.setDouble(3, distanceLimit);

      results = selectStmt.executeQuery();

      while (results.next()) {

    	  int placeId = results.getInt("PlaceId");
          double resultLatitude = results.getDouble("Latitude");
          double resultLongitude = results.getDouble("Longitude");
          String state = results.getString("State");
          double resultDistance = results.getDouble("Total_dist");

          StarGazingPlaces starGazingPlace = new StarGazingPlaces(placeId, resultLatitude, resultLongitude,
              state, resultDistance);
          starGazingPlacesList.add(starGazingPlace);
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

  public StarGazingPlaces delete(StarGazingPlaces starGazingPlaces) throws SQLException {
    String deleteStarGazingPlaces = "DELETE FROM StarGazingPlaces WHERE PlaceId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteStarGazingPlaces);
      deleteStmt.setInt(1, starGazingPlaces.getPlaceId());
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
