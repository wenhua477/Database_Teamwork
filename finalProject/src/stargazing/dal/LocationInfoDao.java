package stargazing.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import stargazing.model.LocationInfo;

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
        "INSERT INTO LocationInfo(Latitude,Longitude,Elevation,Population, State, County, fips) "
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
      insertStmt.setString(5, locationInfo.getState());
      insertStmt.setString(6, locationInfo.getCounty());
      insertStmt.setString(7, locationInfo.getFips());

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


  
  public LocationInfo getLocationInfoByFips(String fip) throws SQLException {
	    String selectLocalInfo =
	        "SELECT *  " +
	            " FROM LocationInfo  " +
	            " WHERE fips=? ;";
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;
	    try {
	      connection = connectionManager.getConnection();
	      selectStmt = connection.prepareStatement(selectLocalInfo);
	      selectStmt.setString(1, fip);
	      results = selectStmt.executeQuery();
	      if (results.next()) {
	    	Double latitude = results.getDouble("Latitude");
	    	Double longitude = results.getDouble("Longitude");
	        int elevation = results.getInt("Elevation");
	        String population = results.getString("Population");
	        String state = results.getString("State");
	        String county = results.getString("County");
	        //String fipRes = results.getString("fips");
	        LocationInfo locationInfo = new LocationInfo(latitude, longitude, elevation, population,
	            state, county, fip);
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
        
        String state = results.getString("State");
        String county = results.getString("County");
        String fip = results.getString("fips");
        LocationInfo locationInfo = new LocationInfo(latitude, longitude, elevation, population,
            state, county, fip);
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
