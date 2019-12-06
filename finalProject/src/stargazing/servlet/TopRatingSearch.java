package stargazing.servlet;

import stargazing.dal.*;
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
import javax.servlet.http.HttpSession;

@WebServlet("/TopRating")
public class TopRatingSearch  extends HttpServlet{

	protected StarGazingPlacesDao starGazingPlaces;
	@Override
	public void init() throws ServletException {
		starGazingPlaces = StarGazingPlacesDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
				Map<String, String> messages = new HashMap<String, String>();
				req.setAttribute("messages", messages);

				HttpSession sess = req.getSession(true);
//				sess.getAttribute()
				System.out.println("hi");
				
				
				Double lati = (Double)sess.getAttribute("lati");
				Double longti = (Double)sess.getAttribute("longt");
				Double r = (Double)sess.getAttribute("radis");
				
				
				List<StarGazingPlaces> starGazingPlacesRes = new ArrayList<StarGazingPlaces>();
				
				try {
					System.out.println("there");
					starGazingPlacesRes = starGazingPlaces.getStargazingPlacesByLatitudeAndLongitudeAndRate(lati, longti, r);
					System.out.println("there2");
					req.setAttribute("places", starGazingPlacesRes);

					req.getRequestDispatcher("/TopRating.jsp").forward(req, resp);
				} catch (SQLException e) {
					// 
					System.out.println("here");
					e.printStackTrace();
				}
				
				
//				req.setAttribute("places", starGazingPlacesRes);
//
//				req.getRequestDispatcher("/TopRating.jsp").forward(req, resp);

	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/TopRating.jsp").forward(req, resp);
		
	}
	
	
}
