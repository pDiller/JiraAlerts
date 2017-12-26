package io.reflectoring.jiraalerts.application.login;

/**
 * Is thrown when the user can´t get logged in.
 */
public class UserNotLoggedInException extends RuntimeException {

	/**
	 * Constructor.
	 *
	 * @param message
	 *            Exceptionmessage.
	 */
	public UserNotLoggedInException(String message) {
		super(message);
	}
}
