package io.reflectoring.jiraalerts.login;

public class UserNotLoggedInException extends RuntimeException {

	public UserNotLoggedInException(String message) {
		super(message);
	}
}
