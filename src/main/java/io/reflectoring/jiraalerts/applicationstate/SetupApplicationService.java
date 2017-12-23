package io.reflectoring.jiraalerts.applicationstate;

import static io.reflectoring.jiraalerts.application.ApplicationState.ACTIVE;

import java.net.URISyntaxException;
import java.net.UnknownHostException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.RestClientException;

import io.reflectoring.jiraalerts.application.ApplicationStateService;
import io.reflectoring.jiraalerts.jiraclient.JiraRestClientService;

/**
 * Provides functionality to setup the application against an JIRA-instance.
 */
@Service
@Transactional
public class SetupApplicationService {

	@Inject
	private JiraRestClientService jiraRestClientService;

	@Inject
	private ApplicationStateService applicationStateService;

	@Inject
	private JiraConnectionRepository jiraConnectionRepository;

	/**
	 * Executes the initial application setup.
	 *
	 * @param jiraLoginDTO
	 *            values which are needed to connect against JIRA.
	 */
	public void setupApplicaton(JiraLoginDTO jiraLoginDTO) {
		try {
			JiraRestClient jiraRestClient = jiraRestClientService.getJiraRestClient(jiraLoginDTO.getUrl(), jiraLoginDTO.getUsername(),
			        jiraLoginDTO.getPassword());
			loginToJira(jiraRestClient);
			applicationStateService.setApplicationState(ACTIVE);
			storeJiraConnection(jiraLoginDTO);
		} catch (URISyntaxException e) {
			throw new SetupApplicationFailedException("The given url is not valid", e);
		}
	}

	private void storeJiraConnection(JiraLoginDTO jiraLoginDTO) {
		JiraConnection jiraConnection = new JiraConnection();
		jiraConnection.setUrl(jiraLoginDTO.getUrl());
		jiraConnection.setUsername(jiraLoginDTO.getUsername());
		jiraConnectionRepository.save(jiraConnection);
	}

	private void loginToJira(JiraRestClient jiraRestClient) {
		try {
			jiraRestClient.getSessionClient().getCurrentSession().claim();
		} catch (RestClientException restClientException) {
			throw new SetupApplicationFailedException("The credentials of the user are not correct", restClientException);
		} catch (Exception exception) {
			// UnknownHostException from JiraRestClient is wrapped in RuntimeException
			Throwable cause = exception.getCause();
			if (cause != null && UnknownHostException.class.isAssignableFrom(cause.getClass())) {
				throw new SetupApplicationFailedException("There is no JIRA instance for given url", exception);
			}
			throw new SetupApplicationFailedException("Setup of application failed", exception);
		}
	}
}
