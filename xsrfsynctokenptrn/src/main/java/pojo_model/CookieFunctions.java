package pojo_model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This POJO (Plain Old Java Object), or model object, is used to provide a basic functionality to help 
 * the LogoutController Servlet, which is to remove the session id cookie from the client browser.
 * 
 * @author Karawalaya - Isuru Samarasekara
 * @since 2019-05-17
 */
public class CookieFunctions {
	
	/**
	 * This method is used to invalidate the existing session cookie in the browser.
	 * @param response This is the first parameter to cookiesInvalidate method which is of type HttpServletResponse.
	 * @return HttpServletResponse This returns the response with the overwritten cookie, with the same name as the 
	 * existing one in the browser which is set to be destroyed as soon as the client browser receives them
	 */
	public static HttpServletResponse cookiesInvalidate(HttpServletResponse response) {
		Cookie sessionCookieRemove = new Cookie("userSesID", "");
		sessionCookieRemove.setMaxAge(0);
		response.addCookie(sessionCookieRemove);
		
		return response;
	}
	
	/**
	 * This method is written to validate the session id of the user via sessionCookie.
	 * @param request  This is the first parameter to sessionValidationBySessionCookie method which is of type HttpServletRequest.
	 * @return boolean This returns true if there is a matching cookie which contains the session id of the client.
	 */
	public static boolean sessionValidationBySessionCookie(HttpServletRequest request) {
		String userSessionID = request.getSession(false).getId();

		if (userSessionID == null) return false;
		
	    String cookieSessionID = null;
	    Cookie[] cookies = request.getCookies();
	    for(Cookie cookie : cookies) {
	    	if(cookie.getName().equals("userSesID")) {
	    		cookieSessionID = cookie.getValue();

	    	return userSessionID.equals(cookieSessionID);
	    	}
	    }
	    return false;
	}
	
	/**
	 * This method is written to get the session id of the user form the session cookie.
	 * @param request  This is the first parameter to getSessionIdFromSessionCookie method which is of type HttpServletRequest.
	 * @return String This returns the session id of the user held in the session cookie.
	 */
	public static String getSessionIdFromSessionCookie(HttpServletRequest request) {
	    String cookieSessionID = null;
	    Cookie[] cookies = request.getCookies();
	    for(Cookie cookie : cookies) {
	    	if(cookie.getName().equals("userSesID")) {
	    		cookieSessionID = cookie.getValue();
	    		break;
	    	}
	    }
	    return cookieSessionID;
	}
}