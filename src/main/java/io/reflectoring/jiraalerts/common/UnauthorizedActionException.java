package io.reflectoring.jiraalerts.common;

import org.apache.wicket.authorization.AuthorizationException;

/**
 * Exception for unauthorized actions.
 */
public class UnauthorizedActionException extends AuthorizationException {

	/**
	 * Constructor.
	 *
	 * @param message
	 *            the exceptionmessage.
	 */
	public UnauthorizedActionException(String message) {
		super(message);
	}

	/**
	 * Constructor.
	 *
	 * @param message
	 *            the exceptionmessage.
	 * @param cause
	 *            the cause of tis exception.
	 */
	public UnauthorizedActionException(String message, Throwable cause) {
		super(message, cause);
	}
}
