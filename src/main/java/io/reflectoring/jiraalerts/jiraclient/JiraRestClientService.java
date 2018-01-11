package io.reflectoring.jiraalerts.jiraclient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

import io.reflectoring.jiraalerts.application.state.ApplicationStateService;
import io.reflectoring.jiraalerts.dashboard.applicationstate.JiraConnection;
import io.reflectoring.jiraalerts.dashboard.applicationstate.JiraConnectionRepository;

/**
 * Service to get a {@link JiraRestClient}.
 */
@Service
public class JiraRestClientService {

	@Inject
	private JiraConnectionRepository jiraConnectionRepository;

	@Inject
	private ApplicationStateService applicationStateService;

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

	/**
	 * Gets a pre initialized {@link JiraRestClient} with stored {@link JiraConnection} data. Throws {@link JiraRestClientInstantiationException} when
	 * there is no JIRA-connection stored ({@link io.reflectoring.jiraalerts.application.state.ApplicationState#NOT_INITIALIZED}) or no password set
	 * ({@link io.reflectoring.jiraalerts.application.state.ApplicationState#NOT_ACTIVE}).
	 *
	 * @return pre initialized {@link JiraRestClient}.
	 * @throws URISyntaxException
	 *             When the given URL is not in usual syntax of URL.
	 */
	public JiraRestClient getInitializedJiraRestClient() throws URISyntaxException {
		List<JiraConnection> jiraConnections = jiraConnectionRepository.findAll();
		if (jiraConnections.isEmpty()) {
			throw new JiraRestClientInstantiationException("There is no JIRA configured");
		}

		String jiraPassword = applicationStateService.getJiraPassword();
		if (jiraPassword == null) {
			throw new JiraRestClientInstantiationException("There is no JIRA password set");
		}

		JiraConnection jiraConnection = jiraConnections.get(0);
		return getJiraRestClient(jiraConnection.getUrl(), jiraConnection.getUsername(), jiraPassword);
	}
}
