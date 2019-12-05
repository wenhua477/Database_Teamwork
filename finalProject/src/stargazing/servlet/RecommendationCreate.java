package stargazing.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import stargazing.dal.RecommendationsDao;
import stargazing.dal.ReviewsDao;
import stargazing.dal.UsersDao;
import stargazing.model.Recommendations;
import stargazing.model.Reviews;
import stargazing.model.Users;
import stargazing.model.Users.UserLevel;

@WebServlet("/addrecommendation")
public class RecommendationCreate extends HttpServlet {
	protected RecommendationsDao recommendationsDao;

	  @Override
	  public void init() throws ServletException {
		  recommendationsDao = RecommendationsDao.getInstance();
	  }


	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		
		HttpSession session = req.getSession();
		
		
		int usrId = (Integer)session.getAttribute("userid");
		// Retrieve and validate name.
		String placeid = (String)session.getAttribute("placeid");
		//String placeid = req.getParameter("placeid");
		// Create the Recommendation.
		
		try {
			Recommendations recommendation = new Recommendations(Integer.parseInt(placeid), usrId);
			recommendationsDao.create(recommendation);
			messages.put("success", "Successfully created " + recommendation);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		
		

		resp.sendRedirect(req.getContextPath() + "/showplace?placeid=" + placeid);
	}
}
