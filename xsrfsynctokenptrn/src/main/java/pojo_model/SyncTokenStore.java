package pojo_model;

import java.util.HashMap;
import java.util.Map;

/**
 * This POJO (Plain Old Java Object), or model object, is used to store 'synchronizer tokens' against the user's session id, 
 * as well as retrieve a particular token based on the user's session id.
 * 
 * @author Karawalaya - Isuru Samarasekara
 * @since 2019-05-17
 */
public class SyncTokenStore {
	private static Map<String, String> syncTokenStore = new HashMap<String, String>();
	
	public static void addToTokenStore(String userSessionID, String token) {
		syncTokenStore.put(userSessionID, token);
	}
	
	public static String getRelevantToken(String userSessionID) {
		return syncTokenStore.get(userSessionID);
	}
}
