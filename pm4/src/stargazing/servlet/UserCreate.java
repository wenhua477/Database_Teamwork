package stargazing.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import stargazing.dal.UsersDao;
import stargazing.model.Users;
import stargazing.model.Users.UserLevel;


@WebServlet("/usercreate")
public class UserCreate extends HttpServlet {

  protected UsersDao usersDao;

  @Override
  public void init() throws ServletException {
    usersDao = UsersDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    //Just render the JSP.
    req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve and validate name.
    String userName = req.getParameter("username");
    if (userName == null || userName.trim().isEmpty()) {
      messages.put("success", "Invalid UserName");
    } else {
      // Create the User.
      String firstName = req.getParameter("firstname");
      String lastName = req.getParameter("lastname");
      String email = req.getParameter("email");
      String phone = req.getParameter("phone");
      String street = req.getParameter("street");
      String city = req.getParameter("city");
      String state = req.getParameter("state");
      String zip = req.getParameter("zip");
      String password = req.getParameter("password");
      try {
        // Exercise: parse the input for StatusLevel.
        Users user = new Users(userName, password, firstName, lastName, email, phone, street, city,
            state, zip,
            UserLevel.normal);
        user = usersDao.create(user);
        messages.put("success", "Successfully created " + userName);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
  }
}
