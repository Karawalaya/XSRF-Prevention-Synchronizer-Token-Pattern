package pojo_model;

import java.util.HashMap;
import java.util.Map;

public class SyncTokenStore {
	private static Map<String, String> syncTokenStore = new HashMap<String, String>();
	
	public static void addToTokenStore(String sessionID, String token) {
		syncTokenStore.put(sessionID, token);
	}
	
	public static String getRelevantToken(String sessionID) {
		return syncTokenStore.get(sessionID);
	}
}
