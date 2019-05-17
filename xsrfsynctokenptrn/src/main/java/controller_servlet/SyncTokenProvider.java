package controller_servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import pojo_model.SyncTokenStore;

/**
 * * This SyncTokenProvider Controller is the Servlet designed to send the relevant synchronizer token to the browser.
 * 
 * This controller, when the legitimateForm.jsp's Ajax call requests the token of the user, finds the user's synchronizer 
 * token, from the SyncTokenStore, using the user's session id. Then the token is put into a map using the variable name 
 * as 'syncToken'. The map is then turned to a json data format and is passed to the page.
 * 
 * @author Karawalaya - Isuru Samarasekara
 * @since 2019-05-17
 */
public class SyncTokenProvider extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession(false);
		String sessionUserName = (String) userSession.getAttribute("sessionUserName");
		
		if (null == sessionUserName) {
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('You cannot go back!');");
			out.println("</script>");
			
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		}
		
		if (userSession != null) {
			response.setStatus(200);

			Map<String, String> returnMap = new HashMap<String, String>();
			returnMap.put("syncToken", SyncTokenStore.getRelevantToken(userSession.getId()));
			String json = new Gson().toJson(returnMap);
			response.setContentType("application/json");
			response.getWriter().write(json);
	    }
	}
}
