package io.reflectoring.jiraalerts.applicationstate;

import static io.reflectoring.jiraalerts.application.ApplicationState.ACTIVE;
import static io.reflectoring.jiraalerts.application.ApplicationState.NOT_ACTIVE;
import static io.reflectoring.jiraalerts.application.ApplicationState.NOT_INITIALIZED;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.Mockito.*;

import java.net.URISyntaxException;
import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.RestClientException;
import com.atlassian.jira.rest.client.api.SessionRestClient;
import com.atlassian.jira.rest.client.api.domain.Session;
import com.atlassian.util.concurrent.Promise;

import io.reflectoring.jiraalerts.application.ApplicationStateService;
import io.reflectoring.jiraalerts.jiraclient.JiraRestClientService;

@RunWith(MockitoJUnitRunner.class)
public class SetupApplicationServiceTest {

	private static final String TEST_URL = "https://jira.test.url";
	private static final String TEST_PASSWORD = "Test123_";
	private static final String TEST_USERNAME = "tester";

	@Mock
	private JiraRestClientService jiraRestClientServiceMock;

	@Mock
	private ApplicationStateService applicationStateServiceMock;

	@Mock
	private JiraConnectionRepository jiraConnectionRepositoryMock;

	@Mock
	private Promise<Session> promise;

	@Mock
	private JiraRestClient jiraRestClientMock;

	@Mock
	private SessionRestClient sessionRestClientMock;

	@Mock
	private Session session;

	@InjectMocks
	private SetupApplicationService testSubject = new SetupApplicationService();

	private JiraLoginDTO jiraLoginDTO;

	@Before
	public void setUp() throws Exception {
		jiraLoginDTO = new JiraLoginDTO();
		jiraLoginDTO.setUrl(TEST_URL);
		jiraLoginDTO.setPassword(TEST_PASSWORD);
		jiraLoginDTO.setUsername(TEST_USERNAME);

		when(promise.claim()).thenReturn(session);
		when(sessionRestClientMock.getCurrentSession()).thenReturn(promise);
		when(jiraRestClientMock.getSessionClient()).thenReturn(sessionRestClientMock);
		when(jiraRestClientServiceMock.getJiraRestClient(TEST_URL, TEST_USERNAME, TEST_PASSWORD)).thenReturn(jiraRestClientMock);
	}

	@Test
	public void setupApplicationCallsJiraRestClientToPerformLogin() throws Exception {
		testSubject.setupApplicaton(jiraLoginDTO);

		verify(jiraRestClientServiceMock).getJiraRestClient(TEST_URL, TEST_USERNAME, TEST_PASSWORD);
	}

	@Test
	public void whenUrlIsNotValidExceptionIsThrown() throws Exception {
		doThrow(new URISyntaxException("", "")).when(jiraRestClientServiceMock).getJiraRestClient(TEST_URL, TEST_USERNAME, TEST_PASSWORD);
		try {
			testSubject.setupApplicaton(jiraLoginDTO);
			failBecauseExceptionWasNotThrown(SetupApplicationFailedException.class);
		} catch (SetupApplicationFailedException exception) {
			assertThat(exception.getMessage()).isEqualTo("The given url is not valid");
		}
	}

	@Test
	public void whenCredentialsAreNotCorrectSetupFailedExceptionIsRethrown() throws Exception {
		doThrow(new RestClientException(mock(Throwable.class))).when(promise).claim();
		try {
			testSubject.setupApplicaton(jiraLoginDTO);
			failBecauseExceptionWasNotThrown(SetupApplicationFailedException.class);
		} catch (SetupApplicationFailedException exception) {
			assertThat(exception.getMessage()).isEqualTo("The credentials of the user are not correct");
		}
	}

	@Test
	public void whenUnknownHostExceptionIsThrownSetupFailedExceptionIsRethrown() throws Exception {
		RuntimeException runtimeException = mock(RuntimeException.class);
		when(runtimeException.getCause()).thenReturn(mock(UnknownHostException.class));
		doThrow(runtimeException).when(promise).claim();
		try {
			testSubject.setupApplicaton(jiraLoginDTO);
			failBecauseExceptionWasNotThrown(SetupApplicationFailedException.class);
		} catch (SetupApplicationFailedException exception) {
			assertThat(exception.getMessage()).isEqualTo("There is no JIRA instance for given url");
		}
	}

	@Test
	public void whenRandomExceptionIsThrownSetupFailedExceptionIsRethrown() throws Exception {
		doThrow(new RuntimeException()).when(promise).claim();
		try {
			testSubject.setupApplicaton(jiraLoginDTO);
			failBecauseExceptionWasNotThrown(SetupApplicationFailedException.class);
		} catch (SetupApplicationFailedException exception) {
			assertThat(exception.getMessage()).isEqualTo("Setup of application failed");
		}
	}

	@Test
	public void whenRandomExceptionWithRandomCauseIsThrownSetupFailedExceptionIsRethrown() throws Exception {
		RuntimeException runtimeExceptionMock = mock(RuntimeException.class);
		when(runtimeExceptionMock.getCause()).thenReturn(mock(RuntimeException.class));
		doThrow(runtimeExceptionMock).when(promise).claim();
		try {
			testSubject.setupApplicaton(jiraLoginDTO);
			failBecauseExceptionWasNotThrown(SetupApplicationFailedException.class);
		} catch (SetupApplicationFailedException exception) {
			assertThat(exception.getMessage()).isEqualTo("Setup of application failed");
		}
	}

	@Test
	public void applicationStateIsSetToActive() throws Exception {
		testSubject.setupApplicaton(jiraLoginDTO);

		verify(applicationStateServiceMock).setApplicationState(ACTIVE);
	}

	@Test
	public void jiraConnectionIsStored() throws Exception {
		testSubject.setupApplicaton(jiraLoginDTO);

		ArgumentCaptor<JiraConnection> argumentCaptor = ArgumentCaptor.forClass(JiraConnection.class);
		verify(jiraConnectionRepositoryMock).save(argumentCaptor.capture());

		assertThat(argumentCaptor.getValue()).isNotNull();
		assertThat(argumentCaptor.getValue().getUsername()).isEqualTo(TEST_USERNAME);
		assertThat(argumentCaptor.getValue().getUrl()).isEqualTo(TEST_URL);
	}

	@Test
	public void whenRepositoryNotFindAnyConfigurationServiceSetsApplicationStateNotInitialized() throws Exception {
		when(jiraConnectionRepositoryMock.findAll()).thenReturn(emptyList());

		testSubject.initializeApplicationState();

		verify(applicationStateServiceMock).setApplicationState(NOT_INITIALIZED);
	}

	@Test
	public void whenRepositoryFindsAnyConfigurationServiceSetsApplicationStateNotActive() throws Exception {
		when(jiraConnectionRepositoryMock.findAll()).thenReturn(singletonList(new JiraConnection()));

		testSubject.initializeApplicationState();

		verify(applicationStateServiceMock).setApplicationState(NOT_ACTIVE);
	}
}