package stargazing.servlet;
import stargazing.dal.StarGazingPlacesDao;
import stargazing.dal.UsersDao;
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




/**
 * FindUsers is the primary entry point into the application.
 * 
 * Note the logic for doGet() and doPost() are almost identical. However, there is a difference:
 * doGet() handles the http GET request. This method is called when you put in the /findusers
 * URL in the browser.
 * doPost() handles the http POST request. This method is called after you click the submit button.
 * 
 */
@WebServlet("/findstargazingplace")
public class FindStargazingPlace extends HttpServlet {
	
	protected StarGazingPlacesDao stargazingPlacesDao;
	
	@Override
	public void init() throws ServletException {
		stargazingPlacesDao = StarGazingPlacesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        req.getRequestDispatcher("/FindStarGazingPlaces.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<StarGazingPlaces> starGazingPlaces = new ArrayList<StarGazingPlaces>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String latitude = req.getParameter("latitude");
        String longitude = req.getParameter("longitude");
        String radius = req.getParameter("radius");
        double r = 0;
        
        if (latitude == null || latitude.trim().isEmpty() || longitude == null || longitude.trim().isEmpty()) {
            messages.put("success", "Please enter valid longitude and latitude.");
        }else {

        boolean numeric = true;
//        set the default distance to 50.
        if (radius == null || radius.trim().isEmpty()) {
        	r = 50;
        }
//        if radius is double, r will be overwritten as the right value.
        try {
            r = Double.parseDouble(radius);
        } catch (NumberFormatException e) {
            numeric = false;
        }
        try {
            Double try_r = Double.parseDouble(latitude);
        } catch (NumberFormatException e) {
            numeric = false;
        }
        try {
            Double try_r = Double.parseDouble(longitude);
        } catch (NumberFormatException e) {
            numeric = false;
        }
        if (!numeric) {
        	messages.put("success", "Please enter valid inputs.");
        }
        
        else {
        	// Retrieve places, and store as a message.
        	try {
        		starGazingPlaces = stargazingPlacesDao.getStargazingPlacesByLatitudeAndLongitude(Double.parseDouble(latitude), 
        				Double.parseDouble(longitude), r);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying stargazing places near " + "Latitude "+latitude + "  Longitude"+longitude);
        }
        }
        req.setAttribute("places", starGazingPlaces);
        
        req.getRequestDispatcher("/FindStarGazingPlaces.jsp").forward(req, resp);
    
        }
}
