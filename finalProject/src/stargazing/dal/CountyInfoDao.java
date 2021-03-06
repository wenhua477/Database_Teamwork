package stargazing.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import stargazing.model.CountyInfo;

public class CountyInfoDao {
protected ConnectionManager connectionManager;
	
	private static CountyInfoDao instance = null;
	protected CountyInfoDao() {
		connectionManager = new ConnectionManager();
	}
	public static CountyInfoDao getInstance() {
		if(instance == null) {
			instance = new CountyInfoDao();
		}
		return instance;
	}
	
	public CountyInfo create(CountyInfo countyInfo)throws SQLException {
		
		String insertCountyInfo = "INSERT INTO CountyInfo(CountyName, StateName, CrimeRate, fips) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCountyInfo);
			
			insertStmt.setString(1, countyInfo.getCountyName());
			insertStmt.setString(2, countyInfo.getStateName());
			insertStmt.setDouble(3, countyInfo.getCrimeRate());
			insertStmt.setString(4, countyInfo.getFips());
			
			insertStmt.executeUpdate();
			
			return countyInfo;
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			if (connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	
	
	public CountyInfo getCountyInfoByFips(String fip) throws SQLException {
		String selectCountyInfo = "SELECT * FROM CountyInfo WHERE fips=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCountyInfo);
			selectStmt.setString(1, fip);
			
			results = selectStmt.executeQuery();

			if(results.next()) {
				
				String resultCountyName = results.getString("CountyName");
				String resultStateName = results.getString("StateName");
				double crimeRate = results.getDouble("CrimeRate");
//				String fip = results.getString("fips");
				
				
				CountyInfo countyInfo = new CountyInfo(resultCountyName, resultStateName, crimeRate, fip);
				return countyInfo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

	
	
	
	
	public CountyInfo getCountyInfoByCountyName(String countyName, String stateName) throws SQLException {
		String selectCountyInfo = "SELECT * FROM CountyInfo WHERE CountyName=? AND StateName =?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCountyInfo);
			selectStmt.setString(1, countyName);
			selectStmt.setString(2, stateName);
			
			results = selectStmt.executeQuery();

			if(results.next()) {
				
				String resultCountyName = results.getString("CountyName");
				String resultStateName = results.getString("StateName");
				double crimeRate = results.getDouble("CrimeRate");
				String fip = results.getString("fips");
				
				
				CountyInfo countyInfo = new CountyInfo(resultCountyName, resultStateName, crimeRate, fip);
				return countyInfo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

	
	public CountyInfo delete(CountyInfo countyInfo)  throws SQLException{
		String deleteCountyInfo = "DELETE FROM CountyInfo WHERE CountyName=? AND StateName =?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCountyInfo);
			
			deleteStmt.setString(1, countyInfo.getCountyName());
			deleteStmt.setString(2, countyInfo.getStateName());
			deleteStmt.executeUpdate();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
		
	}
}
