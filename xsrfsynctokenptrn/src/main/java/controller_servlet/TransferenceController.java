package controller_servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojo_model.CookieFunctions;
import pojo_model.SyncTokenStore;

/**
 * * This TransferenceController is the Servlet designed to validate a transaction.
 * 
 * This controller, with the help of the CookieFunctions.java class, validates whether the form-contained hidden synchronizer 
 * token is equal to the token held by the server, which is retrieved from the SyncTokenStore using the session id of the user.
 * 
 * If the token is validated, the transaction is carried out (represented by the 'Successfully Transfered!') success message, and 
 * else if the token is not similar to token stored in the SyncTokenStore against the user's session id, then the 
 * transaction is not carried out (represented by the 'Transfer Unsuccessful!' error message)
 * 
 * @author Karawalaya - Isuru Samarasekara
 * @since 2019-05-17
 */
public class TransferenceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Post method implementation to handle data sent via the method 'post'.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		String clientHiddenToken = request.getParameter("hiddenTokenField");
		String serverSyncToken = SyncTokenStore.getRelevantToken(userSession.getId());

		if (serverSyncToken.equals(clientHiddenToken)) {
			response.getWriter().append("Sucessfully Transfered!");
		}
		else {
//			response = CookieFunctions.cookiesInvalidate(response);
//			userSession.removeAttribute("sessionUserName");
//			userSession.invalidate();
			
//			PrintWriter out = response.getWriter();
//			out.println("<script type=\"text/javascript\">");
//			out.println("alert('Transfer Unsuccessfull!');");
//			out.println("location='/xsrfsynctokenptrn/views/login.jsp';");
//			out.println("</script>");
			
//			response.sendRedirect("/xsrfsynctokenptrn/views/login.jsp");
			
			response.getWriter().append("Transfer Unsuccessful!");
		}
	}
}
