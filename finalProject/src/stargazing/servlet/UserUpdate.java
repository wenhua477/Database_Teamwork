package stargazing.servlet;

import stargazing.dal.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import stargazing.model.Users;


@WebServlet("/userupdate")
public class UserUpdate extends HttpServlet {

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

		// Retrieve user and validate.
		String userId = req.getParameter("userid");
		if (userId == null || userId.trim().isEmpty()) {
			messages.put("success", "Please enter a valid UserId.");
		} else {
			try {
				Users user = usersDao.getUserById(Integer.parseInt(userId));
				if(user == null) {
					messages.put("success", "UserId does not exist.");
				}
				req.setAttribute("user", user);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}

		req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve user and validate.
		String userId = req.getParameter("userid");
		if (userId == null || userId.trim().isEmpty()) {
			messages.put("success", "Please enter a valid UserId.");
		} else {
			try {
				Users user = usersDao.getUserById(Integer.parseInt(userId));
				if(user == null) {
					messages.put("success", "UserId does not exist. No update to perform.");
				} else {
					String newUserName = req.getParameter("username");
					String newFirstName = req.getParameter("firstname");
					String newLastName = req.getParameter("lastname");
					String newEmail = req.getParameter("email");
					String newPhone = req.getParameter("phone");
					String newStreet = req.getParameter("street");
					String newCity = req.getParameter("city");
					String newState = req.getParameter("state");
					String newZip = req.getParameter("zip");

//					if (newLastName == null || newLastName.trim().isEmpty()) {
//						messages.put("success", "Please enter a valid LastName.");
//					} else {
					user = usersDao.updateUserName(user, newUserName);
					user = usersDao.updateFirstname(user, newFirstName);
					user = usersDao.updateLastName(user, newLastName);
					user = usersDao.updateEmail(user, newEmail);
					user = usersDao.updatePhone(user, newPhone);
					user = usersDao.updateStreet(user, newStreet);
					user = usersDao.updateCity(user, newCity);
					user = usersDao.updateState(user, newState);
					user = usersDao.updateZip(user, newZip);

					
					messages.put("success", "Successfully updated user with id " + userId);
//					}
				}
				req.setAttribute("user", user);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}

		req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
	}
}
