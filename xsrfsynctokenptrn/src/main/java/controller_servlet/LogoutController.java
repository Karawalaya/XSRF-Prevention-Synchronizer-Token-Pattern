package controller_servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojo_model.CookieFunctions;

/**
 * This LogoutController is the Servlet designed to handle the logging out of the user.
 * 
 * This controller removes session attribute 'sessionUserName', saved by the LoginController - 
 * to maintain the user's session across multiple front end pages - and invalidates and removes the session itself.
 * Also, when the user logs out, the session cookie saved at the client browser is also destroyed by overwriting it to 
 * expire as soon as it is set again.
 * 
 * At the end of the get function the use is directed to the login.jsp.
 * 
 * @author Karawalaya - Isuru Samarasekara
 * @since 2019-05-17
 */
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CookieFunctions.cookiesInvalidate(response);
		
		HttpSession userSession = request.getSession(false);
		userSession.removeAttribute("sessionUserName");
		userSession.invalidate();
		
		response.sendRedirect("/xsrfsynctokenptrn/views/login.jsp");
	}

	/**
	 *
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}