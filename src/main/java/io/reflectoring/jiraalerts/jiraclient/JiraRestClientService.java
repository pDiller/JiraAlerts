package io.reflectoring.jiraalerts.jiraclient;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

/**
 * Service to get a {@link JiraRestClient}.
 */
@Service
@ApplicationScope
public class JiraRestClientService {

	private JiraRestClient jiraRestClient;

	/**
	 * Gets a {@link JiraRestClient} for the given URL.
	 *
	 * @param jiraConnectionUrl
	 *            URL for JIRA.
	 * @param jiraUserName
	 *            JIRA username.
	 * @param jiraPassword
	 *            JIRA password.
	 * @return The initialized {@link JiraRestClient}.
	 * @throws URISyntaxException
	 *             When the given URL is not in usual syntax of URL.
	 */
	public JiraRestClient getJiraRestClient(String jiraConnectionUrl, String jiraUserName, String jiraPassword) throws URISyntaxException {
		final AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
		final URI jiraServerUri = new URI(jiraConnectionUrl);
		jiraRestClient = factory.createWithBasicHttpAuthentication(jiraServerUri, jiraUserName, jiraPassword);
		return jiraRestClient;
	}

	/**
	 * Gets a pre initialized {@link JiraRestClient}.
	 *
	 * @return pre initialized {@link JiraRestClient}.
	 */
	public JiraRestClient getInitializedJiraRestClient() {
		return jiraRestClient;
	}
}
