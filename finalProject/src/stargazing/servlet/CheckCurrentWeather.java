package stargazing.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

import stargazing.dal.CampsitesDao;
import stargazing.dal.StarGazingPlacesDao;
import stargazing.dal.UsersDao;
import stargazing.model.Campsites;
import stargazing.model.CountyInfo;
import stargazing.model.LocationInfo;
import stargazing.model.Reviews;
import stargazing.model.StarGazingPlaces;
import stargazing.model.Users;
import stargazing.model.WeatherInfor;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.CurrentWeather;


@WebServlet("/CurrentWeather")
public class CheckCurrentWeather extends HttpServlet {
	protected CampsitesDao campsitesDao;

	@Override
	public void init() throws ServletException {
		campsitesDao = CampsitesDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		OWM owm = new OWM("bb3b5625ca6815def6f74fb8159f4ab8");
		 
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        Campsites place = null;
		
        String placeIdString = req.getParameter("placeid");
        System.out.println(placeIdString);
        
        CurrentWeather cwd = null;
  
        
        WeatherInfor weatherInfor = new WeatherInfor();
        
        System.out.println("hi here");
        if (placeIdString == null || placeIdString.trim().isEmpty()) {
        	System.out.println("sth is wrong");
            messages.put("seccess", "Place enter valid place id.");
        } else {
        	System.out.println("cyw");
            try {
                int placeId = Integer.parseInt(placeIdString);
                place = campsitesDao.getCampsitesById(placeId);
                Double latitude = place.getLatitude();
                Double longtitude = place.getLongitude();
                
        		try {
        			cwd = owm.currentWeatherByCoords(latitude,longtitude);
        			//cwd = owm.currentWeatherByCoords(11,21);
        		} catch (APIException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
                
        		
        	     // checking data retrieval was successful or not
                if (cwd.hasRespCode() && cwd.getRespCode() == 200) {
                
                    // checking if city name is available
                    if (cwd.hasCityName()) {
                        //printing city name from the retrieved data
                        //System.out.println("City: " + cwd.getCityName());
                        weatherInfor.setCityName(cwd.getCityName());
                        
                    }
                    
                    // checking if max. temp. and min. temp. is available
                    if (cwd.hasMainData() && cwd.getMainData().hasTempMax() && cwd.getMainData().hasTempMin()) {
                        // printing the max./min. temperature
                        System.out.println("Temperature: " + cwd.getMainData().getTempMax()
                                    + "/" + cwd.getMainData().getTempMin() + "\'K");
                       
                        weatherInfor.setTemperature(cwd.getMainData().getTempMin()+ "-"+ cwd.getMainData().getTempMax()+"K");
                       
                    }
                    System.out.println("----");
                    System.out.println(cwd.hasRainData());
                    weatherInfor.setHasRain(cwd.hasRainData());
                    
                    
                    
                    req.setAttribute("weatherInfor", weatherInfor);
                    
                }
        		
                

            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying information for place with id being " + placeIdString);
            messages.put("previousPlaceId", placeIdString);       
        }
        
        

       
        req.getRequestDispatcher("/CurrentWeather.jsp").forward(req, resp);
        

	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}


}


