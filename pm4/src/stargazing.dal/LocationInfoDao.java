package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.LocationInfo;

public class LocationInfoDao {

  protected ConnectionManager connectionManager;

  private static LocationInfoDao instance = null;

  protected LocationInfoDao() {
    connectionManager = new ConnectionManager();
  }

  public static LocationInfoDao getInstance() {
    if (instance == null) {
      instance = new LocationInfoDao();
    }
    return instance;
  }


  public LocationInfo create(LocationInfo locationInfo) throws SQLException {
    // Insert into the superclass table first.

    String insertLocationInfo =
        "INSERT INTO LocationInfo(Latitude,Longitude,Elevation,Population,Zip, State, County) "
            + "  VALUES(?,?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertLocationInfo);

      insertStmt.setDouble(1, locationInfo.getLatitude());
      insertStmt.setDouble(2, locationInfo.getLongitude());
      insertStmt.setInt(3, locationInfo.getElevation());
      insertStmt.setString(4, locationInfo.getPopulation());
      insertStmt.setString(5, locationInfo.getZip());
      insertStmt.setString(6, locationInfo.getState());
      insertStmt.setString(7, locationInfo.getCounty());

      insertStmt.executeUpdate();
      return locationInfo;
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


  public LocationInfo getLocationInfoByLatitudeAndLongitude(double latitude, double longitude)
      throws SQLException {
    String selectCampsites =
        "SELECT *  " +
            " FROM LocationInfo  " +
            " WHERE Latitude=? AND Longitude=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectCampsites);
      selectStmt.setDouble(1, latitude);
      selectStmt.setDouble(2, longitude);
      results = selectStmt.executeQuery();
      if (results.next()) {
        int elevation = results.getInt("Elevation");
        String population = results.getString("Population");
        String zip = results.getString("Zip");
        String state = results.getString("State");
        String county = results.getString("County");
        LocationInfo locationInfo = new LocationInfo(latitude, longitude, elevation, population,
            zip, state, county);
        return locationInfo;
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


  public LocationInfo delete(LocationInfo locationInfo) throws SQLException {
    String deleteLocationInfo = "DELETE FROM LocationInfo WHERE Latitude=? AND Longitude=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteLocationInfo);
      deleteStmt.setDouble(1, locationInfo.getLatitude());
      deleteStmt.setDouble(2, locationInfo.getLongitude());
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
