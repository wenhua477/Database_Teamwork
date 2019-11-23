package stargazing.servlet;
import stargazing.dal.StarGazingPlacesDao;
import stargazing.dal.CampsitesDao;
import stargazing.dal.LocationInfoDao;
import stargazing.dal.CountyInfoDao;
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

@WebServlet("/showplace")
public class ShowPlace extends HttpServlet {
    
//    protected StarGazingPlacesDao stargazingPlacesDao;
    protected CampsitesDao campsitesDao;
    protected LocationInfoDao locationInfoDao;
    protected CountyInfoDao countyInfoDao;
    protected ReviewsDao reviewsDao;

    @Override
    public void init() throws ServletException {
//        stargazingPlacesDao = StarGazingPlacesDao.getInstance();
    	campsitesDao = CampsitesDao.getInstance();
    	locationInfoDao = LocationInfoDao.getInstance();
    	countyInfoDao = CountyInfoDao.getInstance();
    	reviewsDao = ReviewsDao.getInstance();
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        Campsites place = null;
        LocationInfo location = null;
        CountyInfo county = null;
        List<Reviews> reviews = new ArrayList<Reviews>();
        
        String placeIdString = req.getParameter("placeid");
        if (placeIdString == null || placeIdString.trim().isEmpty()) {
            messages.put("seccess", "Place enter valid place id.");
        } else {
            try {
                int placeId = Integer.parseInt(placeIdString);
                place = campsitesDao.getCampsitesById(placeId);
         
                String fips = place.getFips();
                location = locationInfoDao.getLocationInfoByFips(fips);
                System.out.println(fips);
                county = countyInfoDao.getCountyInfoByFips(fips);
                System.out.println(county);
                reviews = reviewsDao.getReviewsByPlaceId(placeId);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying information for place with id being " + placeIdString);
            messages.put("previousPlaceId", placeIdString);       
        }
        req.setAttribute("place", place);
        req.setAttribute("location", location);
        req.setAttribute("county", county);
        req.setAttribute("reviews", reviews);
        
        req.getRequestDispatcher("/ShowPlace.jsp").forward(req, resp);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
      
        Campsites place = null;
        String placeIdString = req.getParameter("placeid");
        if (placeIdString == null || placeIdString.trim().isEmpty()) {
            messages.put("seccess", "Place enter valid place id.");
        } else {
            try {
                int placeId = Integer.parseInt(placeIdString);
                place = campsitesDao.getCampsitesById(placeId);
                
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying results for place with id being " + placeIdString);
            messages.put("previousPlaceId", placeIdString);       
        }
        req.setAttribute("place", place);
        
        req.getRequestDispatcher("/ShowPlace.jsp").forward(req, resp);
    }

}
