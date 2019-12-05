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

@WebServlet("/Top10byElevation")
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

				HttpSession sess = req.getSession(true);
//				sess.getAttribute()
				System.out.println("hi");
				
				List<StarGazingPlaces> temp = (List<StarGazingPlaces>)sess.getAttribute("places");
				
				Double lati = (Double)sess.getAttribute("lati");
				Double longti = (Double)sess.getAttribute("longt");
				Double r = (Double)sess.getAttribute("radis");
				
				
				List<TotalInfor> starGazingPlaces = new ArrayList<TotalInfor>();
				
        		
				
				try {
					System.out.println("there");
					starGazingPlaces = totalInforDao.getStargazingPlacesAndOrderByElevation(lati, longti, r);
					System.out.println("there2");
				} catch (SQLException e) {
					// 
					System.out.println("here");
					e.printStackTrace();
				}
				
				System.out.println(starGazingPlaces.size());
				
				req.setAttribute("places", starGazingPlaces);

				req.getRequestDispatcher("/Top10byElevation.jsp").forward(req, resp);

	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/Top10byElevation.jsp").forward(req, resp);
		
	}
	
	
}
