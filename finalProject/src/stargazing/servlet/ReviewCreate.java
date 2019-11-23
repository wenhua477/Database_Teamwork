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

import stargazing.dal.ReviewsDao;
import stargazing.dal.UsersDao;
import stargazing.model.Reviews;
import stargazing.model.Users;
import stargazing.model.Users.UserLevel;

@WebServlet("/addComment")
public class ReviewCreate extends HttpServlet {
	protected ReviewsDao reviewsDao;

	  @Override
	  public void init() throws ServletException {
		  reviewsDao = ReviewsDao.getInstance();
	  }


	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		HttpSession session = req.getSession();
		int usrId = (Integer)session.getAttribute("userid");
		// Retrieve and validate name.
		String comment = req.getParameter("comment");
		String rating = req.getParameter("rating");
		String placeid = req.getParameter("placeid");
		Date date = new Date();

		// Create the User.
		
		
		try {
			// Exercise: parse the input for StatusLevel.
			Reviews review = new Reviews(date, comment, Double.parseDouble(rating), usrId, Integer.parseInt(placeid));
			reviewsDao.create(review);
			messages.put("success", "Successfully created " + review);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e);
		}
//		req.getRequestDispatcher("/showplace?placeid=" + placeid).forward(req, resp);

		resp.sendRedirect(req.getContextPath() + "/showplace?placeid=" + placeid);
	}
}
