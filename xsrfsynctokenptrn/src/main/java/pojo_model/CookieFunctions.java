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
	
	public static HttpServletResponse cookiesInvalidate(HttpServletResponse response) {
		Cookie sessionCookieRemove = new Cookie("userSesID", "");
		sessionCookieRemove.setMaxAge(0);
		response.addCookie(sessionCookieRemove);
		
		return response;
	}
}