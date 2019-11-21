package stargazing.servlet;

import stargazing.dal.FootprintsDao;
import stargazing.dal.RecommendationsDao;
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


@WebServlet("/top10recommendedplaces")
public class CheckTopRecommended extends HttpServlet {

	protected RecommendationsDao recommendationsDao;

	@Override
	public void init() throws ServletException {
		recommendationsDao = RecommendationsDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
				Map<String, String> messages = new HashMap<String, String>();
				req.setAttribute("messages", messages);

				List<StarGazingPlaces> starGazingPlaces = new ArrayList<StarGazingPlaces>();
				try {
					starGazingPlaces = recommendationsDao.getTop10Recommended();
				} catch (SQLException e) {
					// 
					e.printStackTrace();
				}
				
				req.setAttribute("places", starGazingPlaces);

				req.getRequestDispatcher("/Top10RecommendedPlaces.jsp").forward(req, resp);

	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/FindStarGazingPlaces.jsp").forward(req, resp);
		
	}

}

