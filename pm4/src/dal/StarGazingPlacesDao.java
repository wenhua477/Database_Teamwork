package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.StarGazingPlaces;

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
