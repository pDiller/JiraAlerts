package io.reflectoring.jiraalerts.jiraclient;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.atlassian.jira.rest.client.api.JiraRestClient;

import io.reflectoring.jiraalerts.application.state.ApplicationStateService;
import io.reflectoring.jiraalerts.dashboard.applicationstate.JiraConnection;
import io.reflectoring.jiraalerts.dashboard.applicationstate.JiraConnectionRepository;

@RunWith(MockitoJUnitRunner.class)
public class JiraRestClientServiceTest {

	private static final String JIRA_USERNAME = "username";
	private static final String JIRA_URL = "https://test.test";
	private static final String JIRA_PASSWORD = "password";

	@Mock
	private JiraConnectionRepository jiraConnectionRepositoryMock;

	@Mock
	private ApplicationStateService applicationStateServiceMock;

	@InjectMocks
	private JiraRestClientService testSubject;

	@Test
	public void getJiraRestClientReturnsJiraRestClient() throws Exception {
		JiraRestClient jiraRestClient = testSubject.getJiraRestClient(JIRA_URL, JIRA_USERNAME, JIRA_PASSWORD);

		assertThat(jiraRestClient).isNotNull();
	}

	@Test
	public void getInitializedJiraRestClientThrowsExceptionWhenNoJiraIsConfigured() throws Exception {
		try {
			testSubject.getInitializedJiraRestClient();
			failBecauseExceptionWasNotThrown(JiraRestClientInstantiationException.class);
		} catch (JiraRestClientInstantiationException exception) {
			assertThat(exception.getMessage()).isEqualTo("There is no JIRA configured");
		}
	}

	@Test
	public void getInitializedJiraRestClientThrowsExceptionWhenNoPasswordIsSet() throws Exception {
		JiraConnection jiraConnection = new JiraConnection();
		when(jiraConnectionRepositoryMock.findAll()).thenReturn(singletonList(jiraConnection));
		try {
			testSubject.getInitializedJiraRestClient();
			failBecauseExceptionWasNotThrown(JiraRestClientInstantiationException.class);
		} catch (JiraRestClientInstantiationException exception) {
			assertThat(exception.getMessage()).isEqualTo("There is no JIRA password set");
		}
	}

	@Test
	public void getInitializedJiraRestClientReturnsRestClient() throws Exception {
		JiraConnection jiraConnection = new JiraConnection();
		jiraConnection.setUrl(JIRA_URL);
		jiraConnection.setUsername(JIRA_USERNAME);
		when(jiraConnectionRepositoryMock.findAll()).thenReturn(singletonList(jiraConnection));
		when(applicationStateServiceMock.getJiraPassword()).thenReturn(JIRA_PASSWORD);

		JiraRestClient initializedJiraRestClient = testSubject.getInitializedJiraRestClient();

		assertThat(initializedJiraRestClient).isNotNull();
	}
}
