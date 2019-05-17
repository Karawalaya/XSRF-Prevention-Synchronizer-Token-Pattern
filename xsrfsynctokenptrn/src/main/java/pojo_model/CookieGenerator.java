package pojo_model;

import java.util.function.Function;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 * This POJO (Plain Old Java Object), or model object, is used to generate cookies.
 * 
 * It creates a cookie based on the session id (session id is derived using the session object) of 
 * the user, sent by the Login Controller.
 * 
 * @author Karawalaya - Isuru Samarasekara
 * @since 2019-05-17
 */
public class CookieGenerator {
	public static final Function<HttpSession, Cookie> SESSION_ID_COOKIE = new Function<HttpSession, Cookie>() {
		public Cookie apply(HttpSession userSession) {
			return new Cookie("userSesID", userSession.getId());
		}
	};
}