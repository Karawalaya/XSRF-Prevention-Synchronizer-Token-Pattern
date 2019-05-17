package controller_servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao_service.LoginDao;
import pojo_model.User;
import pojo_model.CookieFunctions;
import pojo_model.CookieGenerator;
import pojo_model.SyncTokenGenerator;
import pojo_model.SyncTokenStore;

/**
 * This LoginController is the Servlet designed to handle the login functionality.
 * 
 * This controller checks whether the user credentials are valid, sets the session parameters, generates a unique
 * token, stores that token against the user's session id in memory, creates a cookie - using the session id, 
 * sets the cookie to the response and sends it to the client browser.
 * 
 * At the end of the post function, 
 * if the user is authenticated, he/she is directed to the dashboard.jsp,
 * else, he/she is directed to the login.jsp.
 * 
 * @author Karawalaya - Isuru Samarasekara
 * @since 2019-05-17
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Post method implementation to handle data sent via the method 'post'.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User(request.getParameter("username"), request.getParameter("password"));

		if (LoginDao.isValid(user)) {
			HttpSession userSession = request.getSession(true);
			userSession.setAttribute("sessionUserName", user.getUsername());
			
			String userSessionID = userSession.getId();
			
			String xsrfSyncToken = SyncTokenGenerator.generateSyncToken(userSessionID);
			SyncTokenStore.addToTokenStore(userSessionID, xsrfSyncToken);
			
			response.addCookie(CookieGenerator.SESSION_ID_COOKIE.apply(userSession));

			response.sendRedirect("/xsrfsynctokenptrn/views/dashboard.jsp");
		}
	    else {
	    	response = CookieFunctions.cookiesInvalidate(response);
	    	
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Incorrect Username or Password!');");
			out.println("location='/xsrfsynctokenptrn/views/login.jsp';");
			out.println("</script>");
	    }
	}
}
