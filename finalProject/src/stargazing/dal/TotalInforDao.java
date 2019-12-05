package stargazing.dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import stargazing.model.*;

public class TotalInforDao {
	protected ConnectionManager connectionManager;

	  private static TotalInforDao instance = null;
	  
	  
	  protected TotalInforDao() {
		    connectionManager = new ConnectionManager();
		  }

	  
	  public static TotalInforDao getInstance() {
	    if (instance == null) {
	      instance = new TotalInforDao();
	    }
	    return instance;
	  }
	  
		  
	  public List<TotalInfor> getStargazingPlacesAndOrderByElevation() throws SQLException {
		    //    1 latitude = 111km.
		    String selectStarGazingPlaces =
		        "SELECT * "
		            + " FROM temp "
		            + " LEFT OUTER JOIN"
		            + " SELECT Elevation, Population  " 
		            + " FROM LocationInfo  " 
		            + " ON temp.Latitude = LocationInfo.Latitude AND temp.Longitude = LocationInfo.Longitude "
		            + "	ORDER BY LocationInfo.Elevation DESC";

		    
		    List<TotalInfor> starGazingPlacesList = new ArrayList<TotalInfor>();
		    Connection connection = null;
		    PreparedStatement selectStmt = null;
		    ResultSet results = null;
		    try {
		      connection = connectionManager.getConnection();
		      selectStmt = connection.prepareStatement(selectStarGazingPlaces);

		      results = selectStmt.executeQuery();

		      while (results.next()) {
		    	  TotalInforDao totalI = new TotalInforDao();
		    	  
		    	  	int placeId = results.getInt("PlaceId");		    	  
			    	Double latitude = results.getDouble("Latitude");
			    	Double longitude = results.getDouble("Longitude");
			        String state = results.getString("State");
			        double dis = results.getDouble("Total_dist");
			        String fips = results.getString("fips");
			        int elevation = results.getInt("Elevation");
			        String population = results.getString("Population");

			        TotalInfor place = new TotalInfor(placeId, latitude, longitude, state,dis, fips, elevation, population);
			        
			        
			        if (place != null) {
			        	starGazingPlacesList.add(place);
			        }

		      	}
		      return starGazingPlacesList;
		      }
		     catch (SQLException e) {
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
		    
		  }
	  
	
}
