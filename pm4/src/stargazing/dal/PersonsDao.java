package stargazing.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.*;
import stargazing.model.Persons;

public class PersonsDao {
	protected ConnectionManager connectionManager;
	private static PersonsDao instance = null;
	
	protected PersonsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static PersonsDao getInstance() {
		if(instance == null) {
			instance = new PersonsDao();
		}
		return instance;
	}
	
	public Persons create(Persons person) throws SQLException {
		String insertPerson = "INSERT INTO Persons(UserName, Password)"
				+ " VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPerson,Statement.RETURN_GENERATED_KEYS);

			insertStmt.setString(1, person.getUserName());
			insertStmt.setString(2, person.getPassword());
			
			
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int userId = -1;
			if(resultKey.next()) {
				userId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			person.setUserId(userId);;
			return person;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	
	public Persons getPersonById(int userId) throws SQLException {
		String selectPerson = "SELECT * FROM Persons WHERE UserId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectPerson);
				selectStmt.setInt(1, userId);
				results = selectStmt.executeQuery();
				
				
				if(results.next()) {
					int resultUserId = results.getInt("UserId");
					String userName = results.getString("UserName");
					String password = results.getString("Password");
					
					Persons person = new Persons(resultUserId, userName, password);
					return person;
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
	 
	 public Persons delete(Persons person) throws SQLException{
			String deletePerson = "DELETE FROM Persons WHERE userId=?;";
			
			Connection connection = null;
			PreparedStatement deleteStmt = null;
			try {
				connection = connectionManager.getConnection();
				deleteStmt = connection.prepareStatement(deletePerson);
				deleteStmt.setInt(1, person.getUserId());
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
