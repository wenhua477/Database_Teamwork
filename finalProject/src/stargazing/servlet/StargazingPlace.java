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

import stargazing.dal.StarGazingPlacesDao;
import stargazing.dal.UsersDao;
import stargazing.model.StarGazingPlaces;
import stargazing.model.Users;

@WebServlet("/stargazingplace")
public class StargazingPlace extends HttpServlet {
	protected StarGazingPlacesDao stargazingPlacesDao;

	@Override
	public void init() throws ServletException {
		stargazingPlacesDao = StarGazingPlacesDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve user and validate.
		String placeId = req.getParameter("placeid");
		System.out.println(placeId);
		if (placeId == null || placeId.trim().isEmpty()) {
			messages.put("success", "Please enter a valid Place.");
		} else {
			try {
				StarGazingPlaces place = stargazingPlacesDao.getStarGazingPlacesById(Integer.parseInt(placeId));
				if(place == null) {
					messages.put("success", "Place does not exist.");
				}
				req.setAttribute("place", place);
				System.out.println(place);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}

		req.getRequestDispatcher("/StargazingPlace.jsp").forward(req, resp);
	}

}
