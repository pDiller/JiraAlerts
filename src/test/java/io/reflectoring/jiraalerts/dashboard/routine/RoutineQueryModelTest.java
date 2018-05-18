package io.reflectoring.jiraalerts.dashboard.routine;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.application.testsetup.TestApplication;

@RunWith(MockitoJUnitRunner.class)
public class RoutineQueryModelTest {

	private static final long QUERY_ID = 4711;

	@Mock
	private RoutineQueryService routineQueryServiceMock;

	@Before
	public void setUp() throws Exception {
		new WicketTester(new TestApplication(this));
	}

	@Test
	public void getObjectCallsService() throws Exception {
		RoutineQueryModel routineQueryModel = new RoutineQueryModel(QUERY_ID);

		routineQueryModel.getObject();

		verify(routineQueryServiceMock).loadRoutineQueryDTOById(QUERY_ID);
	}

	@Test
	public void getObjectReturnsCorrectRoutineQuery() throws Exception {
		RoutineQueryDTO routineQueryDTO = new RoutineQueryDTO();
		when(routineQueryServiceMock.loadRoutineQueryDTOById(QUERY_ID)).thenReturn(routineQueryDTO);
		RoutineQueryModel routineQueryModel = new RoutineQueryModel(QUERY_ID);

		RoutineQueryDTO loadedRoutineQueryDTO = routineQueryModel.getObject();

		assertThat(loadedRoutineQueryDTO).isEqualTo(routineQueryDTO);
	}
}
