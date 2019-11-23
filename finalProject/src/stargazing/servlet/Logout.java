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
import javax.servlet.http.HttpSession;

import stargazing.dal.PersonsDao;
import stargazing.model.Persons;

@WebServlet("/logout")
public class Logout extends HttpServlet {
	@Override
	public void init() throws ServletException {
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		HttpSession session = req.getSession();
		session.removeAttribute("username");
		session.setAttribute("Logstate","Log In");
		session.setAttribute("Logstatehref","login");
		req.getRequestDispatcher("/FindStarGazingPlaces.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
