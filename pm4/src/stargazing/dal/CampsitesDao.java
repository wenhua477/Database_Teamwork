package stargazing.dal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import stargazing.model.Campsites;
import stargazing.model.StarGazingPlaces;

public class CampsitesDao extends StarGazingPlacesDao {

  protected ConnectionManager connectionManager;

  private static CampsitesDao instance = null;

  protected CampsitesDao() {
    connectionManager = new ConnectionManager();
  }

  public static CampsitesDao getInstance() {
    if (instance == null) {
      instance = new CampsitesDao();
    }
    return instance;
  }

  public Campsites create(Campsites campsites) throws SQLException {
    // Insert into the superclass table first.
    create(new StarGazingPlaces(campsites.getPlaceId(), campsites.getLatitude(),
        campsites.getLongitude(), campsites.getState()));

    String insertObservatory = "INSERT INTO Campsites(PlaceId,Name,Type) VALUES(?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertObservatory);
      insertStmt.setInt(1, campsites.getPlaceId());
      insertStmt.setString(2, campsites.getName());

      insertStmt.setString(3, campsites.getType());
      insertStmt.executeUpdate();
      return campsites;
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

  public Campsites getCampsitesById(int placeId) throws SQLException {
    String selectCampsites =
        "SELECT Campsites.PlaceId AS PlaceId, Latitude, Longitude, State,Name,Type " +
            " FROM Campsites INNER JOIN StarGazingPlaces " +
            " ON Campsites.PlaceId = StarGazingPlaces.PlaceId " +
            " WHERE Campsites.PlaceId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectCampsites);
      selectStmt.setInt(1, placeId);
      results = selectStmt.executeQuery();
      if (results.next()) {

        double latitude = results.getDouble("Latitude");
        double longitude = results.getDouble("Longitude");
        String state = results.getString("State");
        String name = results.getString("Name");
        String type = results.getString("Type");
        Campsites campsites = new Campsites(placeId, latitude, longitude, state, name,
            type);
        return campsites;
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

  public Campsites delete(Campsites campsites) throws SQLException {
    String deleteCampsites = "DELETE FROM Campsites WHERE PlaceId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteCampsites);
      deleteStmt.setInt(1, campsites.getPlaceId());
      deleteStmt.executeUpdate();

      // Then also delete from the superclass.
      // Note: due to the fk constraint (ON DELETE CASCADE), we could simply call
      // super.delete() without even needing to delete from Observatory first.
      super.delete(campsites);

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
