package pojo_model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieFunctions {
	
	public static HttpServletResponse cookiesInvalidate(HttpServletResponse response) {
		Cookie sessionCookieRemove = new Cookie("SessionID", "");
		sessionCookieRemove.setMaxAge(0);
		response.addCookie(sessionCookieRemove);
		
		return response;
	}
}