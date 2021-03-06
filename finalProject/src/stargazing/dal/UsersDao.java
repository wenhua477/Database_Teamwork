package stargazing.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import stargazing.model.Persons;
import stargazing.model.Users;

public class UsersDao extends PersonsDao {

  private static UsersDao instance = null;

  protected UsersDao() {
    super();
  }

  public static UsersDao getInstance() {
    if (instance == null) {
      instance = new UsersDao();
    }
    return instance;
  }

  public Users create(Users user) throws SQLException {
    Persons person = create(new Persons(user.getUserId(), user.getUserName(), user.getPassword()));

    String insertUser = "INSERT INTO Users(UserId,FirstName,LastName,Email,Phone,Street,City,State,Zip,Level) VALUES(?,?,?,?,?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertUser);
      insertStmt.setInt(1, person.getUserId());
      insertStmt.setString(2, user.getFirstName());
      insertStmt.setString(3, user.getLastName());
      insertStmt.setString(4, user.getEmail());
      insertStmt.setString(5, user.getPhone());
      insertStmt.setString(6, user.getStreet());
      insertStmt.setString(7, user.getCity());
      insertStmt.setString(8, user.getState());
      insertStmt.setString(9, user.getZip());
      insertStmt.setString(10, user.getUserLevel().name());

      insertStmt.executeUpdate();
      user.setUserId(person.getUserId());
      return user;
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
  
  
  public Users getUserByUserName(String userName) throws SQLException {
//	  System.out.print(userId * 10);
    String selectUsers =
        "SELECT Users.UserId AS UserId, Persons.UserName AS UserName, Persons.Password AS Password,FirstName, LastName, Email, Phone, Street," 
            +
            "City, State, Zip, Level " +
            "FROM Users LEFT OUTER JOIN Persons " +
            "  ON Users.UserId = Persons.UserId " +
            "WHERE UserName=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectUsers);
      selectStmt.setString(1, userName);
      results = selectStmt.executeQuery();

      if (results.next()) {

    	int userId = results.getInt("UserId");
        String password = results.getString("Password");
        String firstName = results.getString("FirstName");
        String lastName = results.getString("LastName");
        String email = results.getString("Email");
        String phone = results.getString("Phone");
        String street = results.getString("Street");
        String city = results.getString("City");
        String state = results.getString("State");
        String zip = results.getString("Zip");
//				Restaurants.CuisineType cuisineType = Restaurants.
//						CuisineType.valueOf(results.getString("CuisineType"));
        Users.UserLevel level = Users.UserLevel.valueOf(results.getString("Level"));

        Users user = new Users(userId, userName, password, firstName, lastName, email, phone,
            street, city, state, zip, level);
        return user;
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
  
 

  public Users getUserById(int userId) throws SQLException {
//	  System.out.print(userId * 10);
    String selectUsers =
        "SELECT Users.UserId AS UserId, Persons.UserName AS UserName, Persons.Password AS Password,FirstName, LastName, Email, Phone, Street," 
            +
            "City, State, Zip, Level " +
            "FROM Users LEFT OUTER JOIN Persons " +
            "  ON Users.UserId = Persons.UserId " +
            "WHERE Users.UserId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectUsers);
      selectStmt.setInt(1, userId);
      results = selectStmt.executeQuery();

      if (results.next()) {

        String userName = results.getString("UserName");
        String password = results.getString("Password");
        String firstName = results.getString("FirstName");
        String lastName = results.getString("LastName");
        String email = results.getString("Email");
        String phone = results.getString("Phone");
        String street = results.getString("Street");
        String city = results.getString("City");
        String state = results.getString("State");
        String zip = results.getString("Zip");
//				Restaurants.CuisineType cuisineType = Restaurants.
//						CuisineType.valueOf(results.getString("CuisineType"));
        Users.UserLevel level = Users.UserLevel.valueOf(results.getString("Level"));

        Users user = new Users(userId, userName, password, firstName, lastName, email, phone,
            street, city, state, zip, level);
        return user;
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

  public List<Users> getUserByFirstname(String firstName) throws SQLException {
    String selectUsers =
        "SELECT Users.UserId AS UserId, Persons.UserName AS UserName, Persons.Password AS Password, FirstName, LastName, Email, Phone, Street,"
            +
            "City, State, Zip, Level " +
            "FROM Users LEFT OUTER JOIN Persons " +
            "  ON Users.UserId = Persons.UserId " +
            "WHERE Users.FirstName=?;";
    List<Users> users = new ArrayList<Users>();
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectUsers);
      selectStmt.setString(1, firstName);
      results = selectStmt.executeQuery();

      while (results.next()) {

        String userName = results.getString("UserName");
        String password = results.getString("Password");
        String lastName = results.getString("LastName");
        int userId = results.getInt("UserId");
        String email = results.getString("Email");
        String phone = results.getString("Phone");
        String street = results.getString("Street");
        String city = results.getString("City");
        String state = results.getString("State");
        String zip = results.getString("Zip");
        Users.UserLevel level = Users.UserLevel.valueOf(results.getString("Level"));

        Users user = new Users(userId, userName, password, firstName, lastName, email, phone,
            street, city, state, zip, level);
        users.add(user);
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
    return users;
  }


  
//  updateLastName
public Users updateLastName(Users user, String newLastName) throws SQLException {
	if (newLastName == null || newLastName.trim().isEmpty()) {
		return user;
	}
  String updateUser = "UPDATE Users SET LastName=? WHERE UserId=?;";
  Connection connection = null;
  PreparedStatement updateStmt = null;
  try {
    connection = connectionManager.getConnection();
    updateStmt = connection.prepareStatement(updateUser);
    updateStmt.setString(1, newLastName);
    updateStmt.setInt(2, user.getUserId());
    updateStmt.executeUpdate();

    // Update the user param before returning to the caller.
    user.setLastName(newLastName);
    return user;
  } catch (SQLException e) {
    e.printStackTrace();
    throw e;
  } finally {
    if(connection != null) {
      connection.close();
    }
    if(updateStmt != null) {
      updateStmt.close();
    }
  }
}

public Users updateUserName(Users user, String newUserName) throws SQLException {
		if (newUserName == null || newUserName.trim().isEmpty()) {
			return user;
		}
	  String updateUser = "UPDATE Persons SET UserName=? WHERE UserId=?;";
	  Connection connection = null;
	  PreparedStatement updateStmt = null;
	  try {
	    connection = connectionManager.getConnection();
	    updateStmt = connection.prepareStatement(updateUser);
	    updateStmt.setString(1, newUserName);
	    updateStmt.setInt(2, user.getUserId());
	    updateStmt.executeUpdate();

	    // Update the user param before returning to the caller.
	    user.setUserName(newUserName);
	    return user;
	  } catch (SQLException e) {
	    e.printStackTrace();
	    throw e;
	  } finally {
	    if(connection != null) {
	      connection.close();
	    }
	    if(updateStmt != null) {
	      updateStmt.close();
	    }
	  }
	}

public Users updateFirstname(Users user, String newFirstname) throws SQLException {
	if (newFirstname == null || newFirstname.trim().isEmpty()) {
		return user;
	}
  String updateUser = "UPDATE Users SET FirstName=? WHERE UserId=?;";
  Connection connection = null;
  PreparedStatement updateStmt = null;
  try {
    connection = connectionManager.getConnection();
    updateStmt = connection.prepareStatement(updateUser);
    updateStmt.setString(1, newFirstname);
    updateStmt.setInt(2, user.getUserId());
    updateStmt.executeUpdate();

    // Update the user param before returning to the caller.
    user.setFirstName(newFirstname);
    return user;
  } catch (SQLException e) {
    e.printStackTrace();
    throw e;
  } finally {
    if(connection != null) {
      connection.close();
    }
    if(updateStmt != null) {
      updateStmt.close();
    }
  }
}

public Users updateEmail(Users user, String email) throws SQLException {
	if (email == null || email.trim().isEmpty()) {
		return user;
	}
  String updateUser = "UPDATE Users SET Email=? WHERE UserId=?;";
  Connection connection = null;
  PreparedStatement updateStmt = null;
  try {
    connection = connectionManager.getConnection();
    updateStmt = connection.prepareStatement(updateUser);
    updateStmt.setString(1, email);
    updateStmt.setInt(2, user.getUserId());
    updateStmt.executeUpdate();

    // Update the user param before returning to the caller.
    user.setEmail(email);
    return user;
  } catch (SQLException e) {
    e.printStackTrace();
    throw e;
  } finally {
    if(connection != null) {
      connection.close();
    }
    if(updateStmt != null) {
      updateStmt.close();
    }
  }
}

public Users updatePhone(Users user, String phone) throws SQLException {
	if (phone == null || phone.trim().isEmpty()) {
		return user;
	}
  String updateUser = "UPDATE Users SET Phone=? WHERE UserId=?;";
  Connection connection = null;
  PreparedStatement updateStmt = null;
  try {
    connection = connectionManager.getConnection();
    updateStmt = connection.prepareStatement(updateUser);
    updateStmt.setString(1, phone);
    updateStmt.setInt(2, user.getUserId());
    updateStmt.executeUpdate();

    // Update the user param before returning to the caller.
    user.setPhone(phone);
    return user;
  } catch (SQLException e) {
    e.printStackTrace();
    throw e;
  } finally {
    if(connection != null) {
      connection.close();
    }
    if(updateStmt != null) {
      updateStmt.close();
    }
  }
}

public Users updateStreet(Users user, String newStreet) throws SQLException {
	if (newStreet == null || newStreet.trim().isEmpty()) {
		return user;
	}
  String updateUser = "UPDATE Users SET Street=? WHERE UserId=?;";
  Connection connection = null;
  PreparedStatement updateStmt = null;
  try {
    connection = connectionManager.getConnection();
    updateStmt = connection.prepareStatement(updateUser);
    updateStmt.setString(1, newStreet);
    updateStmt.setInt(2, user.getUserId());
    updateStmt.executeUpdate();

    // Update the user param before returning to the caller.
    user.setStreet(newStreet);
    return user;
  } catch (SQLException e) {
    e.printStackTrace();
    throw e;
  } finally {
    if(connection != null) {
      connection.close();
    }
    if(updateStmt != null) {
      updateStmt.close();
    }
  }
}

public Users updateCity(Users user, String newCity) throws SQLException {
	if (newCity == null || newCity.trim().isEmpty()) {
		return user;
	}
  String updateUser = "UPDATE Users SET City=? WHERE UserId=?;";
  Connection connection = null;
  PreparedStatement updateStmt = null;
  try {
    connection = connectionManager.getConnection();
    updateStmt = connection.prepareStatement(updateUser);
    updateStmt.setString(1, newCity);
    updateStmt.setInt(2, user.getUserId());
    updateStmt.executeUpdate();

    // Update the user param before returning to the caller.
    user.setCity(newCity);
    return user;
  } catch (SQLException e) {
    e.printStackTrace();
    throw e;
  } finally {
    if(connection != null) {
      connection.close();
    }
    if(updateStmt != null) {
      updateStmt.close();
    }
  }
}

public Users updateState(Users user, String newState) throws SQLException {
	if (newState == null || newState.trim().isEmpty()) {
		return user;
	}
  String updateUser = "UPDATE Users SET State=? WHERE UserId=?;";
  Connection connection = null;
  PreparedStatement updateStmt = null;
  try {
    connection = connectionManager.getConnection();
    updateStmt = connection.prepareStatement(updateUser);
    updateStmt.setString(1, newState);
    updateStmt.setInt(2, user.getUserId());
    updateStmt.executeUpdate();

    // Update the user param before returning to the caller.
    user.setState(newState);
    return user;
  } catch (SQLException e) {
    e.printStackTrace();
    throw e;
  } finally {
    if(connection != null) {
      connection.close();
    }
    if(updateStmt != null) {
      updateStmt.close();
    }
  }
}
public Users updateZip(Users user, String newZip) throws SQLException {
	if (newZip == null || newZip.trim().isEmpty()) {
		return user;
	}
  String updateUser = "UPDATE Users SET Zip=? WHERE UserId=?;";
  Connection connection = null;
  PreparedStatement updateStmt = null;
  try {
    connection = connectionManager.getConnection();
    updateStmt = connection.prepareStatement(updateUser);
    updateStmt.setString(1, newZip);
    updateStmt.setInt(2, user.getUserId());
    updateStmt.executeUpdate();

    // Update the user param before returning to the caller.
    user.setZip(newZip);
    return user;
  } catch (SQLException e) {
    e.printStackTrace();
    throw e;
  } finally {
    if(connection != null) {
      connection.close();
    }
    if(updateStmt != null) {
      updateStmt.close();
    }
  }
}


  public Users delete(Users user) throws SQLException {
    String deleteUser = "DELETE FROM Users WHERE UserId=?;";

    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteUser);
      deleteStmt.setInt(1, user.getUserId());
      int affectedRows = deleteStmt.executeUpdate();
      if (affectedRows == 0) {
        throw new SQLException("No records available to delete for UserName=" + user.getUserId());
      }

      super.delete(user);

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
