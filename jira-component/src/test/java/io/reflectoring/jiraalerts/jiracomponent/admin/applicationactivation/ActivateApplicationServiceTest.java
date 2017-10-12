package io.reflectoring.jiraalerts.jiracomponent.admin.applicationactivation;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.SessionRestClient;
import com.atlassian.jira.rest.client.api.domain.Session;
import com.atlassian.util.concurrent.Promise;

import io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration.JiraConnectionDataDTO;
import io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration.JiraConnectionDataService;
import io.reflectoring.jiraalerts.jiracomponent.jiraclient.JiraRestClientService;

@RunWith(MockitoJUnitRunner.class)
public class ActivateApplicationServiceTest {

	private static final String ACTIVATION_PASSWORD = "activation";
	private static final String ACTIVATION_USERNAME = "testuser";
	private static final String ACTIVATION_URL = "testjira";

	@Mock
	private JiraRestClientService jiraRestClientServiceMock;

	@Mock
	private JiraConnectionDataService jiraConnectionDataServiceMock;

	@InjectMocks
	private ActivateApplicationService sut = new ActivateApplicationService();

	@Before
	public void setUp() throws Exception {
		JiraConnectionDataDTO jiraConnectionDataDTO = new JiraConnectionDataDTO();
		jiraConnectionDataDTO.setUsername(ACTIVATION_USERNAME);
		jiraConnectionDataDTO.setUrl(ACTIVATION_URL);
		when(jiraConnectionDataServiceMock.getJiraConnectionDataDTO()).thenReturn(jiraConnectionDataDTO);
	}

	@Test
	public void activateApplicationGetsJiraRestClientForActivation() throws Exception {
		sut.activateApplication(ACTIVATION_PASSWORD);

		verify(jiraRestClientServiceMock).getJiraRestClient(ACTIVATION_URL, ACTIVATION_USERNAME, ACTIVATION_PASSWORD);
	}

	@Test
	public void onDefaultApplicationIsLocked() throws Exception {

		assertThat(sut.isApplicationActivated()).isFalse();
	}

	@Test
	public void activateApplicationActivatesTheApplication() throws Exception {
		JiraRestClient jiraRestClientMock = mock(JiraRestClient.class);
		Session jiraSessionMock = mock(Session.class);
		SessionRestClient sessionRestClientMock = mock(SessionRestClient.class);
		Promise<Session> sessionPromiseMock = Mockito.mock(Promise.class);
		when(sessionRestClientMock.getCurrentSession()).thenReturn(sessionPromiseMock);
		when(sessionPromiseMock.claim()).thenReturn(jiraSessionMock);
		when(jiraRestClientMock.getSessionClient()).thenReturn(sessionRestClientMock);
		when(jiraRestClientServiceMock.getJiraRestClient(ACTIVATION_URL, ACTIVATION_USERNAME, ACTIVATION_PASSWORD)).thenReturn(jiraRestClientMock);
		sut.activateApplication(ACTIVATION_PASSWORD);

		assertThat(sut.isApplicationActivated()).isTrue();
	}

	@Test
	public void activateApplicationLoadsJiraConnectionData() throws Exception {
		sut.activateApplication(ACTIVATION_PASSWORD);

		verify(jiraConnectionDataServiceMock).getJiraConnectionDataDTO();
	}
}
