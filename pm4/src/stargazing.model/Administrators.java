package stargaing.model;

import java.sql.Timestamp;

public class Administrators extends Persons {

  protected Timestamp lastLogin;

  public Administrators(int userId) {
    super(userId);
    // TODO Auto-generated constructor stub
  }

  public Administrators(int userId, String userName, String password, Timestamp lastLogin) {
    super(userId, userName, password);
    this.lastLogin = lastLogin;
  }

  public Administrators(String userName, String password, Timestamp lastLogin) {
    super(userName, password);
    this.lastLogin = lastLogin;
  }

  public Timestamp getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(Timestamp lastLogin) {
    this.lastLogin = lastLogin;
  }


}
