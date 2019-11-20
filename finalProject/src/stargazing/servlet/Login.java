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
import javax.servlet.http.HttpSession;

import stargazing.dal.PersonsDao;
import stargazing.dal.UsersDao;
import stargazing.model.Persons;
import stargazing.model.Users;

@WebServlet("/login")
public class Login extends HttpServlet{
	protected PersonsDao personsDao;
	
	@Override
	public void init() throws ServletException {
		personsDao = PersonsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {    
        req.getRequestDispatcher("/Login.jsp").forward(req, resp);
	}
	
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Persons person = null;
        
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            messages.put("success", "Please enter valid username and password.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
            	person = personsDao.getPersonByCredential(username, password);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        }
        req.setAttribute("person", person);
        if(person == null) {
        	messages.put("success", "invalid username or passowrd ");
        	req.getRequestDispatcher("/Login.jsp").forward(req, resp);
        } else {
        	//successfully login
        	messages.put("success", "successfully logined in as " + username);
        	// set session
        	HttpSession session = req.getSession();  
            session.setAttribute("username",username); 
        	req.getRequestDispatcher("/FindStarGazingPlaces.jsp").forward(req, resp);
        }
        
        
    }
}
