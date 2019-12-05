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

public class checkByElevation  extends HttpServlet{

	protected TotalInforDao totalInforDao;

	@Override
	public void init() throws ServletException {
		totalInforDao = TotalInforDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
				Map<String, String> messages = new HashMap<String, String>();
				req.setAttribute("messages", messages);

				

				List<TotalInfor> starGazingPlaces = new ArrayList<TotalInfor>();
				
				
				try {
					starGazingPlaces = totalInforDao.getStargazingPlacesAndOrderByElevation();
				} catch (SQLException e) {
					// 
					e.printStackTrace();
				}
				
				req.setAttribute("places", starGazingPlaces);

				req.getRequestDispatcher("/Top10byElevation.jsp").forward(req, resp);

	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/Top10byCirmeRate.jsp").forward(req, resp);
		
	}
	
	
}
