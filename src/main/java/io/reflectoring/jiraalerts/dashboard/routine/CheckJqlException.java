package io.reflectoring.jiraalerts.dashboard.routine;

/**
 * Exception for Jql errors.
 */
public class CheckJqlException extends RuntimeException {

	/**
	 * Constructor.
	 *
	 * @param message
	 *            exceptionmessage.
	 * @param cause
	 *            the cause of this exception.
	 */
	public CheckJqlException(String message, Throwable cause) {
		super(message, cause);
	}
}
