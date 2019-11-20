package stargazing.servlet;
import stargazing.dal.StarGazingPlacesDao;
import stargazing.model.*;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/showplace")
public class ShowPlace extends HttpServlet {
    
    protected StarGazingPlacesDao stargazingPlacesDao;
    
    @Override
    public void init() throws ServletException {
        stargazingPlacesDao = StarGazingPlacesDao.getInstance();
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        StarGazingPlaces place = null;
        
        String placeIdString = req.getParameter("placeId");
        if (placeIdString == null || placeIdString.trim().isEmpty()) {
            messages.put("seccess", "Place enter valid place id.");
        } else {
            try {
                int placeId = Integer.parseInt(placeIdString);  
                place = stargazingPlacesDao.getStarGazingPlacesById(placeId);               
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying results for place with id being " + placeIdString);
            messages.put("previousPlaceId", placeIdString);       
        }
        req.setAttribute("starGazingPlaces", place);
        
        req.getRequestDispatcher("/ShowPlace.jsp").forward(req, resp);
    }
    

}
