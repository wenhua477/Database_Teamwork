package stargazing.servlet;
import stargazing.dal.StarGazingPlacesDao;
import stargazing.dal.UsersDao;
import stargazing.dal.CampsitesDao;
import stargazing.dal.LocationInfoDao;
import stargazing.dal.RecommendationsDao;
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

@WebServlet("/showuser")
public class ShowUser extends HttpServlet {
    
//    protected StarGazingPlacesDao stargazingPlacesDao;
    protected UsersDao usersDao;
    protected ReviewsDao reviewsDao;
    protected RecommendationsDao recommendationsDao;
    protected StarGazingPlacesDao stargazingPlacesDao;

    @Override
    public void init() throws ServletException {
    	usersDao = UsersDao.getInstance();
    	reviewsDao = ReviewsDao.getInstance();
    	recommendationsDao = RecommendationsDao.getInstance();
    	stargazingPlacesDao = StarGazingPlacesDao.getInstance();
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        Users user = null;
        List<Reviews> reviews = null;
        List<Recommendations> recommendations = null;
        List<Integer> placeIds = new ArrayList<Integer>();
        String userIdString = req.getParameter("userid");
        if (userIdString == null || userIdString.trim().isEmpty()) {
            messages.put("seccess", "Place enter valid place id.");
        } else {
            try {
                int userId = Integer.parseInt(userIdString);
                user = usersDao.getUserById(userId);
                reviews = reviewsDao.getReviewsByUserId(userId);
                recommendations = recommendationsDao.getRecommendationsByUserId(userId);
                for (Recommendations r : recommendations) {
                	placeIds.add(r.getPlaceId());
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying information for place with id being " + userIdString);
//            messages.put("previousPlaceId", userIdString);       
        }
        req.setAttribute("user", user);
        req.setAttribute("reviews", reviews);
        req.setAttribute("places", placeIds);
        
        req.getRequestDispatcher("/ShowUser.jsp").forward(req, resp);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
      
        Users place = null;
        String userId = req.getParameter("userid");
        if (userId == null || userId.trim().isEmpty()) {
            messages.put("seccess", "Place enter valid place id.");
        } else {
            try {
                int id = Integer.parseInt(userId);
                place = usersDao.getUserById(id);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying profile for user with id being " + userId);
//            messages.put("previousPlaceId", placeIdString);       
        }
        req.setAttribute("place", place);
        
        req.getRequestDispatcher("/ShowUser.jsp").forward(req, resp);
    }

}
