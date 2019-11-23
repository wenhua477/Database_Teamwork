package stargazing.servlet;



import java.io.IOException;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/landingPage")
public class landingPage  extends HttpServlet{
	
	
	@Override
	public void init() throws ServletException {
		
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/landingPage.jsp").forward(req, resp);
	}
	
	
}
