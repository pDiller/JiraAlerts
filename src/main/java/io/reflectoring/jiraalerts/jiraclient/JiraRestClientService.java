package io.reflectoring.jiraalerts.jiraclient;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Service;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

/**
 * Service to get a {@link JiraRestClient}.
 */
@Service
public class JiraRestClientService {

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
		return factory.createWithBasicHttpAuthentication(jiraServerUri, jiraUserName, jiraPassword);
	}
}
