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
        "INSERT INTO StarGazingPlaces(PlaceId, Latitude, Longitude, State, fips) " +
            " VALUES(?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertStarGazingPlaces);

      insertStmt.setInt(1, starGazingPlace.getPlaceId());
      insertStmt.setDouble(2, starGazingPlace.getLatitude());
      insertStmt.setDouble(3, starGazingPlace.getLongitude());
      insertStmt.setString(4, starGazingPlace.getState());
      insertStmt.setString(5, starGazingPlace.getFips());

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
        "SELECT PlaceId, Latitude, Longitude, State, fips " +
            " FROM StarGazingPlaces WHERE PlaceId=?;";
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
        String fips = results.getString("fips");
        
        StarGazingPlaces starGazingPlace = new StarGazingPlaces(placeId, latitude, longitude,
            state, fips);
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
      double longitude, double distanceLimit) throws SQLException {
    //    1 latitude = 111km.
    String selectStarGazingPlaces =
        "SELECT PlaceId, Latitude, Longitude, State, SQRT(POW(Latitude - ?, 2) + POW(Longitude - ?, 2)) AS Total_dist "
            + " FROM StarGazingPlaces "
            + " HAVING Total_dist < ? "
            + " ORDER BY Total_dist ASC; ";
    
    
    String createTempTable =  
    		"DROP TABLE IF EXISTS temp;"
    		+ "CREATE TABLE temp "
    		+ "  as (SELECT PlaceId, "
    		+ "Latitude, Longitude, State, fips"
    		+ " FROM StarGazingPlaces );";
    

   
    
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
       CampsitesDao campsiteDao = new CampsitesDao();
         ObservatoryDao observatoryDao = new ObservatoryDao();
         StarGazingPlaces place1 = campsiteDao.getCampsitesById(placeId);
         StarGazingPlaces place2 = observatoryDao.getObservatoryById(placeId);
         if (place1 != null) {
          place1.setDistance(results.getDouble("Total_dist") * 70.0);
          starGazingPlacesList.add(place1);
         } else if (place2 != null){
          starGazingPlacesList.add(place2);
          place2.setDistance(results.getDouble("Total_dist")  * 70.0);
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
  
  
  public List<StarGazingPlaces> getStargazingPlacesByLatitudeAndLongitudeAndRate(double latitude,
	      double longitude, double distanceLimit) throws SQLException {
	    //    1 latitude = 111km.
	    String selectStarGazingPlaces =
	    		"	SELECT s.PlaceId, s.Latitude, s.Longitude, s.State, SQRT(POW(Latitude - ?, 2) + POW(Longitude - ?, 2)) AS Total_dist," + 
	    				"    r.Rating" + 
	    				"    from StarGazingPlaces s" + 
	    				"    inner join  Reviews r" + 
	    				"    on s.PlaceId = r.PlaceId" + 
	    				"	HAVING Total_dist < ?"+
	    				"	ORDER BY r.Rating DESC";
	    						    
	    

	    
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
	        double latit = results.getDouble("Latitude");
	        double longi = results.getDouble("Longitude");
	        String state = results.getString("State");
	        //String fips = results.getString("fips");
	        
	        StarGazingPlaces place1 = new StarGazingPlaces(placeId, latitude, longitude,
	            state);
	        
	        System.out.println("0");
	         if (place1 != null) {
	          place1.setDistance(results.getDouble("Total_dist") * 70.0);
	          System.out.println("1");
	          starGazingPlacesList.add(place1);
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

	 

}



