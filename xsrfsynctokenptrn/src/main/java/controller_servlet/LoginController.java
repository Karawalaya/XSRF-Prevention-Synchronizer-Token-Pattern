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
import pojo_model.CookieGenerator;
import pojo_model.SyncTokenGenerator;
import pojo_model.SyncTokenStore;

/**
 *
 *
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		User user = new User(request.getParameter("username"), request.getParameter("password"));

		if (LoginDao.isValid(user)) {
			String sessionID = session.getId();
			
			String xsrfSyncToken = SyncTokenGenerator.generateSyncToken(sessionID);
			SyncTokenStore.addToTokenStore(sessionID, xsrfSyncToken);
			response.addCookie(CookieGenerator.SESSION_ID_COOKIE.apply(session));

			response.sendRedirect("/xsrfsynctokenptrn/views/dashboard.jsp");
		}
	    else {
	    	session.invalidate();
	    	
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Incorrect Username or Password!');");
			out.println("location='/xsrfsynctokenptrn/views/login.jsp';");
			out.println("</script>");
	    }
	}
}
