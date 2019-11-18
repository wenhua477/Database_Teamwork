package stargazing.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import stargazing.model.Footprints;

public class FootprintsDao {

  protected ConnectionManager connectionManager;

  private static FootprintsDao instance = null;

  protected FootprintsDao() {
    connectionManager = new ConnectionManager();
  }

  public static FootprintsDao getInstance() {
    if (instance == null) {
      instance = new FootprintsDao();
    }
    return instance;
  }

  public Footprints create(Footprints footprints) throws SQLException {
    String insertFootprints =
        "INSERT INTO Footprints(UserId, PlaceId, TimeVisited) " +
            " VALUES(?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertFootprints);

      insertStmt.setInt(1, footprints.getUserId());
      insertStmt.setInt(2, footprints.getPlaceId());
      insertStmt.setTimestamp(3, new java.sql.Timestamp(footprints.getTimeVisited().getTime()));

      insertStmt.executeUpdate();
      return footprints;
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

  public Footprints getFootprintsById(int footprintId) throws SQLException {
    String selectFootprints =
        "SELECT UserId, PlaceId, TimeVisited " +
            " FROM Footprints WHERE FootprintId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectFootprints);
      selectStmt.setInt(1, footprintId);
      results = selectStmt.executeQuery();
      if (results.next()) {
        int userId = results.getInt("UserId");
        int placeId = results.getInt("PlaceId");
        Date timeVisited = new Date(results.getTimestamp("TimeVisited").getTime());
        Footprints footprints = new Footprints(footprintId, userId, placeId, timeVisited);

        return footprints;
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

  public List<Footprints> getFootprintsByPlaceId(int placeId) throws SQLException {
    List<Footprints> footprints = new ArrayList<Footprints>();
    String selectFootprints =
        "SELECT FootprintId,UserId, TimeVisited,Footprints.PlaceId as PlaceId "
            +
            " FROM Footprints INNER JOIN StarGazingPlaces " +
            " ON Footprints.PlaceId = StarGazingPlaces.PlaceId " +
            " WHERE Footprints.PlaceId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectFootprints);
      selectStmt.setInt(1, placeId);
      results = selectStmt.executeQuery();
      while (results.next()) {
        int footprintId = results.getInt("FootprintId");
        int userId = results.getInt("UserId");
        Date timeVisited = new Date(results.getTimestamp("TimeVisited").getTime());
        int resultedPlaceId = results.getInt("PlaceId");
        Footprints footprint = new Footprints(footprintId,userId,resultedPlaceId,timeVisited);
        footprints.add(footprint);
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
    return footprints;
  }

  public List<Footprints> getFootprintsByUserId(int userId) throws SQLException {
    List<Footprints> footprints = new ArrayList<Footprints>();
    String selectFootprints =
        "SELECT FootprintId,PlaceId,TimeVisited,Footprints.UserId as UserId "
            +
            " FROM Footprints INNER JOIN Users " +
            " ON Footprints.UserId = Users.UserId " +
            " WHERE Footprints.UserId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectFootprints);
      selectStmt.setInt(1, userId);
      results = selectStmt.executeQuery();
      while (results.next()) {
        int footprintId = results.getInt("FootprintId");
        Date timeVisited = new Date(results.getTimestamp("TimeVisited").getTime());
        int resultedPlaceId = results.getInt("PlaceId");
        Footprints footprint = new Footprints(footprintId,userId,resultedPlaceId,timeVisited);
        footprints.add(footprint);
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
    return footprints;
  }

  public Footprints delete(Footprints footprints) throws SQLException {
    String deleteFootprints = "DELETE FROM Footprints WHERE FootprintId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteFootprints);
      deleteStmt.setInt(1, footprints.getFootprintId());
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
