package io.reflectoring.jiraalerts.dashboard.routine;

import static io.reflectoring.jiraalerts.dashboard.routine.RoutineQueryState.NOT_ACTIVE;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

@RunWith(MockitoJUnitRunner.class)
public class RoutineQueryServiceTest {

	private static final long USER_ID = 1337;
	private static final int EXPECTED_COUNT = 5;
	private static final int ROUTINE_ID = 4811;
	private static final String ROUTINE_NAME = "TestRoutine";
	private static final String ROUTINE_JQL = "JQL";
	private static final int ROUTINE_MINUTES = 12;
	private static final RoutineQueryState ROUTINE_STATE = NOT_ACTIVE;

	@Mock
	private RoutineQueryRepository routineQueryRepositoryMock;

	@InjectMocks
	private RoutineQueryService testSubject;

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
}
