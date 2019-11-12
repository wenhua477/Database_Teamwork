package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import model.Administrators;
import model.Persons;


public class AdministratorsDao extends PersonsDao {

  private static AdministratorsDao instance = null;

  protected AdministratorsDao() {
    super();
  }

  public static AdministratorsDao getInstance() {
    if (instance == null) {
      instance = new AdministratorsDao();
    }
    return instance;
  }

  public Administrators create(Administrators administrator) throws SQLException {
    Persons person = create(new Persons(administrator.getUserId(), administrator.getUserName(),
        administrator.getPassword()));

    String insertAdministrator = "INSERT INTO Administrators(UserId,LastLogin) VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertAdministrator);
      insertStmt.setInt(1, person.getUserId());
      insertStmt.setTimestamp(2, administrator.getLastLogin());

      insertStmt.executeUpdate();
      administrator.setLastLogin(administrator.getLastLogin());
      return administrator;
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

  public Administrators getAdministratorsById(int userId) throws SQLException {
    String selectAdministrators =
        "SELECT Administrators.UserId AS UserId, UserName, Password, LastLogin" +
            "FROM Administrators INNER JOIN Persons " +
            "  ON Administrators.UserId = Persons.UserId " +
            "WHERE Administrators.UserId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectAdministrators);
      selectStmt.setInt(1, userId);
      results = selectStmt.executeQuery();

      if (results.next()) {

        String userName = results.getString("UserName");
        String password = results.getString("Password");
        Timestamp lastLogin = results.getTimestamp("LastLogin");

        Administrators administrator = new Administrators(userId, userName, password, lastLogin);
        return administrator;
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


  public Administrators delete(Administrators administrator) throws SQLException {
    String deleteAdministrator = "DELETE FROM Administrators WHERE UserId=?;";

    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteAdministrator);
      deleteStmt.setInt(1, administrator.getUserId());
      int affectedRows = deleteStmt.executeUpdate();
      if (affectedRows == 0) {
        throw new SQLException(
            "No records available to delete for UserName=" + administrator.getUserId());
      }

      super.delete(administrator);

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
