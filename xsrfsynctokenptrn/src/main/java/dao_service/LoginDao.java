package dao_service;

import pojo_model.User;

public class LoginDao {
	  private static final String USERNAME = "LegitBrotherToken";
	  private static final String PASSWORD = "tokenStuff";

	  public static boolean isValid(User user) {
	    return USERNAME.equals(user.getUsername()) && PASSWORD.equals(user.getPassword());
	  }
}
