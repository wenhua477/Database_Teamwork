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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

@WebServlet("/findstargazingplace")
public class FindStargazingPlace extends HttpServlet {

	protected StarGazingPlacesDao stargazingPlacesDao;

	@Override
	public void init() throws ServletException {
		stargazingPlacesDao = StarGazingPlacesDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/FindStarGazingPlaces.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		List<StarGazingPlaces> starGazingPlaces = new ArrayList<StarGazingPlaces>();

		String address = req.getParameter("location");
		double[] latlong = null;
		try {
			latlong = getLatLongPositions(address);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e);
		}

		double latitude = latlong[0];
		double longitude = latlong[1];
		String radius = req.getParameter("radius");
		double r = 0;

		if (latlong == null) {
			messages.put("success", "Please enter valid longitude and latitude.");
		} else {

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
				Double try_r = latitude;
			} catch (NumberFormatException e) {
				numeric = false;
			}
			try {
				Double try_r = longitude;
			} catch (NumberFormatException e) {
				numeric = false;
			}
			if (!numeric) {
				messages.put("success", "Please enter valid inputs.");
			}

			else {
				// Retrieve places, and store as a message.
				try {
					starGazingPlaces = stargazingPlacesDao.getStargazingPlacesByLatitudeAndLongitude(latitude,
							longitude, r);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
				}
				messages.put("success",
						"Displaying stargazing places near "+ address);
			}
		}
		req.setAttribute("places", starGazingPlaces);

		req.getRequestDispatcher("/FindStarGazingPlaces.jsp").forward(req, resp);

	}

		
	// pass in the address of a location, return its coordination
	private double[] getLatLongPositions(String address) throws Exception {
		final String API_KEY = "AIzaSyDaeHXUOXFH5oc2l0pqYOT2CeWsjUv_PSU";
		final GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyDaeHXUOXFH5oc2l0pqYOT2CeWsjUv_PSU")
				.build();

		GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		double latitude = results[0].geometry.location.lat;
		double longitude = results[0].geometry.location.lng;
		System.out.println(latitude);
		System.out.println(longitude);

		return new double[] { latitude, longitude };
	}
}
