package io.reflectoring.jiraalerts.dashboard.routine;

import static io.reflectoring.jiraalerts.dashboard.routine.RoutineQueryState.NOT_ACTIVE;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.util.concurrent.Promise;

import io.reflectoring.jiraalerts.application.login.User;
import io.reflectoring.jiraalerts.application.login.UserRepository;
import io.reflectoring.jiraalerts.common.UnauthorizedActionException;
import io.reflectoring.jiraalerts.jiraclient.JiraRestClientService;

@RunWith(MockitoJUnitRunner.class)
public class RoutineQueryServiceTest {

	private static final long USER_ID = 1337;
	private static final int EXPECTED_COUNT = 5;
	private static final int ROUTINE_ID = 4811;
	private static final String ROUTINE_NAME = "TestRoutine";
	private static final String ROUTINE_JQL = "JQL";
	private static final int ROUTINE_MINUTES = 12;
	private static final RoutineQueryState ROUTINE_STATE = NOT_ACTIVE;
	private static final User ROUTINE_OWNER = new User();

	@Mock
	private UserRepository userRepositoryMock;

	@Mock
	private RoutineQueryRepository routineQueryRepositoryMock;

	@Mock
	private JiraRestClientService jiraRestClientServiceMock;

	@Mock
	private Promise<SearchResult> promiseMock;

	@Mock
	private SearchRestClient searchRestClientMock;

	@Mock
	private JiraRestClient jiraRestClientMock;

	@InjectMocks
	private RoutineQueryService testSubject;

	@Before
	public void setUp() throws Exception {
		when(jiraRestClientServiceMock.getInitializedJiraRestClient()).thenReturn(jiraRestClientMock);
		when(jiraRestClientMock.getSearchClient()).thenReturn(searchRestClientMock);
		when(searchRestClientMock.searchJql(ROUTINE_JQL)).thenReturn(promiseMock);
	}

	@Test
	public void countRoutineQueriesByUserIdCallsRoutineRepositoryMock() throws Exception {
		testSubject.countRoutineQueriesByUserId(USER_ID);

		verify(routineQueryRepositoryMock).countByOwner(USER_ID);
	}

	@Test
	public void countRoutineQueriesByUserIdReturnsValueFromRepository() throws Exception {
		when(routineQueryRepositoryMock.countByOwner(USER_ID)).thenReturn(EXPECTED_COUNT);

		int loadedCount = testSubject.countRoutineQueriesByUserId(USER_ID);

		assertThat(loadedCount).isEqualTo(EXPECTED_COUNT);
	}

	@Test
	public void getRoutineQueriesByUserIdCallsRoutineQueryRepository() throws Exception {
		PageRequest pageRequest = mock(PageRequest.class);
		testSubject.getRoutineQueriesByUserId(USER_ID, pageRequest);

		verify(routineQueryRepositoryMock).findByOwner(USER_ID, pageRequest);
	}

	@Test
	public void getRoutineQueriesByUserIdReturnesUsersRoutineQueries() throws Exception {
		PageRequest pageRequest = mock(PageRequest.class);
		when(routineQueryRepositoryMock.findByOwner(USER_ID, pageRequest)).thenReturn(Arrays.asList(new RoutineQuery(), new RoutineQuery()));

		List<RoutineQueryDTO> routineQueryDTOs = testSubject.getRoutineQueriesByUserId(USER_ID, pageRequest);

		assertThat(routineQueryDTOs).hasSize(2);
	}

	@Test
	public void getRoutineQueriesByUserIdMapsEntityValuesToDTO() throws Exception {
		RoutineQuery routineQuery = new RoutineQuery();
		routineQuery.setId(ROUTINE_ID);
		routineQuery.setName(ROUTINE_NAME);
		routineQuery.setJql(ROUTINE_JQL);
		routineQuery.setMinutesForRecognition(ROUTINE_MINUTES);
		routineQuery.setRoutineQueryState(ROUTINE_STATE);
		PageRequest pageRequest = mock(PageRequest.class);
		when(routineQueryRepositoryMock.findByOwner(USER_ID, pageRequest)).thenReturn(singletonList(routineQuery));

		List<RoutineQueryDTO> routineQueryDTOs = testSubject.getRoutineQueriesByUserId(USER_ID, pageRequest);

		SoftAssertions softAssertions = new SoftAssertions();
		softAssertions.assertThat(routineQueryDTOs.get(0).getId()).isEqualTo(ROUTINE_ID);
		softAssertions.assertThat(routineQueryDTOs.get(0).getName()).isEqualTo(ROUTINE_NAME);
		softAssertions.assertThat(routineQueryDTOs.get(0).getJqlString()).isEqualTo(ROUTINE_JQL);
		softAssertions.assertThat(routineQueryDTOs.get(0).getMinutesForRecognition()).isEqualTo(ROUTINE_MINUTES);
		softAssertions.assertThat(routineQueryDTOs.get(0).getRoutineQueryState()).isEqualTo(ROUTINE_STATE);
		softAssertions.assertAll();
	}

	@Test
	public void saveRoutineQueryCallsRepositoryToSave() throws Exception {
		when(userRepositoryMock.findOne(USER_ID)).thenReturn(ROUTINE_OWNER);
		RoutineQueryDTO routineQueryDTO = new RoutineQueryDTO();
		routineQueryDTO.setName(ROUTINE_NAME);
		routineQueryDTO.setJqlString(ROUTINE_JQL);
		routineQueryDTO.setMinutesForRecognition(ROUTINE_MINUTES);
		testSubject.saveRoutineQuery(routineQueryDTO, USER_ID);

		ArgumentCaptor<RoutineQuery> argumentCaptor = ArgumentCaptor.forClass(RoutineQuery.class);
		verify(routineQueryRepositoryMock).save(argumentCaptor.capture());

		RoutineQuery captorValue = argumentCaptor.getValue();
		SoftAssertions softAssertions = new SoftAssertions();
		softAssertions.assertThat(captorValue.getName()).isEqualTo(ROUTINE_NAME);
		softAssertions.assertThat(captorValue.getJql()).isEqualTo(ROUTINE_JQL);
		softAssertions.assertThat(captorValue.getMinutesForRecognition()).isEqualTo(ROUTINE_MINUTES);
		softAssertions.assertThat(captorValue.getRoutineQueryState()).isEqualTo(NOT_ACTIVE);
		softAssertions.assertThat(captorValue.getOwner()).isEqualTo(ROUTINE_OWNER);
		softAssertions.assertAll();
	}

	@Test
	public void saveRoutineQueryCallsRepositoryToLoadLoggedInUser() throws Exception {
		when(userRepositoryMock.findOne(USER_ID)).thenReturn(ROUTINE_OWNER);
		RoutineQueryDTO routineQueryDTO = new RoutineQueryDTO();
		routineQueryDTO.setJqlString(ROUTINE_JQL);
		testSubject.saveRoutineQuery(routineQueryDTO, USER_ID);

		verify(userRepositoryMock).findOne(USER_ID);
	}

	@Test
	public void saveRoutineQueryThrowsExceptionWhenUserNotFound() throws Exception {
		try {
			RoutineQueryDTO routineQueryDTO = new RoutineQueryDTO();
			routineQueryDTO.setJqlString(ROUTINE_JQL);
			testSubject.saveRoutineQuery(routineQueryDTO, USER_ID);
			failBecauseExceptionWasNotThrown(UnauthorizedActionException.class);
		} catch (UnauthorizedActionException exception) {
			assertThat(exception.getMessage()).isEqualTo("There is no user with Id 1337");
		}
	}

	@Test
	public void checkJqlCallsRestClientService() throws Exception {
		testSubject.checkJql(ROUTINE_JQL);

		verify(jiraRestClientServiceMock).getInitializedJiraRestClient();
	}

	@Test
	public void checkJqlExceptionThrowsCheckJqlExceptionWhenJiraRestClientThrowsExceptionAtCreation() throws Exception {
		RuntimeException toBeThrown = new RuntimeException();
		doThrow(toBeThrown).when(jiraRestClientServiceMock).getInitializedJiraRestClient();
		try {
			testSubject.checkJql(ROUTINE_JQL);
			failBecauseExceptionWasNotThrown(CheckJqlException.class);
		} catch (CheckJqlException exception) {
			assertThat(exception.getCause()).isSameAs(toBeThrown);
		}
	}

	@Test
	public void searchWithInvalidJqlThrowsException() throws Exception {
		RuntimeException toBeThrown = new RuntimeException();
		doThrow(toBeThrown).when(promiseMock).claim();

		try {
			testSubject.checkJql(ROUTINE_JQL);
			failBecauseExceptionWasNotThrown(CheckJqlException.class);
		} catch (CheckJqlException exception) {
			assertThat(exception.getCause()).isSameAs(toBeThrown);
		}
	}
}