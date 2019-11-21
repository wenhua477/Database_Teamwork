package stargazing.servlet;

import stargazing.dal.FootprintsDao;
import stargazing.dal.ReviewsDao;
import stargazing.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/top10ratingplaces")
public class CheckTopRating extends HttpServlet {

	protected ReviewsDao reviewsDao;

	@Override
	public void init() throws ServletException {
		reviewsDao = ReviewsDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
				Map<String, String> messages = new HashMap<String, String>();
				req.setAttribute("messages", messages);

				List<StarGazingPlaces> starGazingPlaces = new ArrayList<StarGazingPlaces>();
				try {
					starGazingPlaces = reviewsDao.getTop10Rated();
				} catch (SQLException e) {
					// 
					e.printStackTrace();
				}
				
				req.setAttribute("places", starGazingPlaces);

				req.getRequestDispatcher("/Top10RatingPlaces.jsp").forward(req, resp);

	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/FindStarGazingPlaces.jsp").forward(req, resp);
		
	}

}

