package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.CountyInfo;

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
		
		String insertCountyInfo = "INSERT INTO CountyInfo(CountyName, CrimeRate) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCountyInfo);
			
			insertStmt.setString(1, countyInfo.getCountyName());
			insertStmt.setDouble(2, countyInfo.getCrimeRate());
			
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
	
	public CountyInfo getCountyInfoByCountyName(String countyName) throws SQLException {
		String selectCountyInfo = "SELECT * FROM CountyInfo WHERE CountyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCountyInfo);
			selectStmt.setString(1, countyName);

			results = selectStmt.executeQuery();

			if(results.next()) {
				String resultCountyName = results.getString("CountyName");
				double crimeRate = results.getDouble("CrimeRate");
				
				
				CountyInfo countyInfo = new CountyInfo(resultCountyName, crimeRate);
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
		String deleteCountyInfo = "DELETE FROM CountyInfo WHERE CountyName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCountyInfo);
			
			deleteStmt.setString(1, countyInfo.getCountyName());
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
