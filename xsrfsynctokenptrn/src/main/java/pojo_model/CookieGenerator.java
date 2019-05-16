package pojo_model;

import java.util.function.Function;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

public class CookieGenerator {
	public static final Function<HttpSession, Cookie> SESSION_ID_COOKIE = new Function<HttpSession, Cookie>() {
		public Cookie apply(HttpSession session) {
			return new Cookie("SessionID", session.getId());
		}
	};
}