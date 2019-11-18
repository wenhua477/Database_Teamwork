package stargazing.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import stargazing.model.Observatory;
import stargazing.model.StarGazingPlaces;

public class ObservatoryDao extends StarGazingPlacesDao {

  protected ConnectionManager connectionManager;

  private static ObservatoryDao instance = null;

  protected ObservatoryDao() {
    connectionManager = new ConnectionManager();
  }

  public static ObservatoryDao getInstance() {
    if (instance == null) {
      instance = new ObservatoryDao();
    }
    return instance;
  }


  public Observatory create(Observatory observatory) throws SQLException {
    // Insert into the superclass table first.
    create(new StarGazingPlaces(observatory.getPlaceId(), observatory.getLatitude(),
        observatory.getLongitude(), observatory.getState()));

    String insertObservatory = "INSERT INTO Observatory(PlaceId,Price,OpenHour) VALUES(?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertObservatory);
      insertStmt.setInt(1, observatory.getPlaceId());
      insertStmt.setDouble(2, observatory.getPrice());

      insertStmt.setString(3, observatory.getOpenHour());
      insertStmt.executeUpdate();
      return observatory;
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

  public Observatory getObservatoryById(int placeId) throws SQLException {
    String selectObservatory =
        "SELECT Observatory.PlaceId AS PlaceId, Latitude, Longitude, State,Price,OpenHour " +
            " FROM Observatory INNER JOIN StarGazingPlaces " +
            " ON Observatory.PlaceId = StarGazingPlaces.PlaceId " +
            " WHERE Observatory.PlaceId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectObservatory);
      selectStmt.setInt(1, placeId);
      results = selectStmt.executeQuery();
      if (results.next()) {

        double latitude = results.getDouble("Latitude");
        double longitude = results.getDouble("Longitude");
        String state = results.getString("State");
        double price = results.getDouble("Price");
        String openHour = results.getString("OpenHour");
        Observatory observatory = new Observatory(placeId, latitude, longitude, state, price,
            openHour);
        return observatory;
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

  public Observatory delete(Observatory observatory) throws SQLException {
    String deleteObservatory = "DELETE FROM Observatory WHERE PlaceId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteObservatory);
      deleteStmt.setInt(1, observatory.getPlaceId());
      deleteStmt.executeUpdate();

      // Then also delete from the superclass.
      // Note: due to the fk constraint (ON DELETE CASCADE), we could simply call
      // super.delete() without even needing to delete from Observatory first.
      super.delete(observatory);

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
