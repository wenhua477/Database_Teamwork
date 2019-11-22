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

import stargazing.dal.StarGazingPlacesDao;
import stargazing.dal.UsersDao;
import stargazing.model.StarGazingPlaces;
import stargazing.model.Users;

import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.CurrentWeather;


@WebServlet("/instantWeather")
public class CheckCurrentWeather extends HttpServlet {
	protected StarGazingPlacesDao stargazingPlacesDao;

	@Override
	public void init() throws ServletException {
		stargazingPlacesDao = StarGazingPlacesDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		OWM owm = new OWM("bb3b5625ca6815def6f74fb8159f4ab8");
		 
        // getting current weather data for the "London" city
        //CurrentWeather cwd = owm.currentWeatherByCityName("London");
        CurrentWeather cwd = null;
		try {
			//cwd = owm.currentWeatherByCityName("London");
			cwd = owm.currentWeatherByCoords(36,121);
		} catch (APIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
     // checking data retrieval was successful or not
        if (cwd.hasRespCode() && cwd.getRespCode() == 200) {
        
            // checking if city name is available
            if (cwd.hasCityName()) {
                //printing city name from the retrieved data
                System.out.println("City: " + cwd.getCityName());
            }
            
            // checking if max. temp. and min. temp. is available
            if (cwd.hasMainData() && cwd.getMainData().hasTempMax() && cwd.getMainData().hasTempMin()) {
                // printing the max./min. temperature
                System.out.println("Temperature: " + cwd.getMainData().getTempMax()
                            + "/" + cwd.getMainData().getTempMin() + "\'K");
            }
        }
       
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}


}


