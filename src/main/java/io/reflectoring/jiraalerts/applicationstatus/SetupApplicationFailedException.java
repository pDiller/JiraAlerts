package io.reflectoring.jiraalerts.applicationstatus;

/**
 * Exception which should be thrown, when setup of application fails.
 */
public class SetupApplicationFailedException extends RuntimeException {

	/**
	 * Constructor.
	 *
	 * @param message
	 *            The reason why application setup is failed.
	 */
	public SetupApplicationFailedException(String message) {
		super(message);
	}

	/**
	 * Constructor.
	 *
	 * @param message
	 *            The reason why application setup is failed.
	 * @param cause
	 *            The nested exception.
	 */
	public SetupApplicationFailedException(String message, Throwable cause) {
		super(message, cause);
	}
}
