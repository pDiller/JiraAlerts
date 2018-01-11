package io.reflectoring.jiraalerts.jiraclient;

/**
 * Exception for errors at instantiation of {@link com.atlassian.jira.rest.client.api.JiraRestClient}.
 */
public class JiraRestClientInstantiationException extends RuntimeException {

	/**
	 * Constructor.
	 *
	 * @param message
	 *            the exceptionmessage.
	 */
	public JiraRestClientInstantiationException(String message) {
		super(message);
	}
}
