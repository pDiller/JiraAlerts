package io.reflectoring.jiraalerts.issuerecognition;

import static io.reflectoring.jiraalerts.application.state.ApplicationState.ACTIVE;
import static io.reflectoring.jiraalerts.application.state.ApplicationState.NOT_ACTIVE;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;

import io.reflectoring.jiraalerts.application.state.ApplicationStateService;
import io.reflectoring.jiraalerts.jiraclient.JiraRestClientService;
import io.reflectoring.jiraalerts.routine.RoutineQuery;
import io.reflectoring.jiraalerts.routine.RoutineQueryRepository;

@RunWith(MockitoJUnitRunner.class)
public class IssueRecognizerServiceTest {

	@Mock
	private JiraRestClientService jiraRestClientServiceMock;

	@Mock
	private ApplicationStateService applicationStateServiceMock;

	@Mock
	private RoutineQueryRepository routineQueryRepositoryMock;

	@InjectMocks
	private IssueRecognizerService testSubject;

	@Test
	public void nothingHappensWhenApplicationStateIsNotActive() throws Exception {
		when(applicationStateServiceMock.getApplicationState()).thenReturn(NOT_ACTIVE);

		testSubject.recognize();

		verifyZeroInteractions(jiraRestClientServiceMock, routineQueryRepositoryMock);
	}

	@Test
	public void recognizeCallsApplicationState() throws Exception {
		testSubject.recognize();

		verify(applicationStateServiceMock).getApplicationState();
	}

	@Test
	public void whenApplicationStateIsActiveAllRoutinesAreLoaded() throws Exception {
		when(applicationStateServiceMock.getApplicationState()).thenReturn(ACTIVE);

		testSubject.recognize();

		verify(routineQueryRepositoryMock).findAllActive();
	}

	@Test
	public void whenApplicationStateIsActiveInitializedRestClientIsLoaded() throws Exception {
		when(applicationStateServiceMock.getApplicationState()).thenReturn(ACTIVE);

		testSubject.recognize();

		verify(jiraRestClientServiceMock).getInitializedJiraRestClient();
	}

	@Test
	public void forEachLoadedRoutineJiraRestClientAsksJiraForIssues() throws Exception {
		JiraRestClient jiraRestClientSpy = spy(JiraRestClient.class);
		when(jiraRestClientSpy.getSearchClient()).thenReturn(mock(SearchRestClient.class, RETURNS_DEEP_STUBS));
		when(jiraRestClientServiceMock.getInitializedJiraRestClient()).thenReturn(jiraRestClientSpy);

		when(applicationStateServiceMock.getApplicationState()).thenReturn(ACTIVE);

		when(routineQueryRepositoryMock.findAllActive()).thenReturn(asList(new RoutineQuery(), new RoutineQuery()));

		testSubject.recognize();

		verify(jiraRestClientSpy, times(2)).getSearchClient();
	}
}
