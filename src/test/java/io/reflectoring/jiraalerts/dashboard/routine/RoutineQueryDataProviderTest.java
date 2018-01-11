package io.reflectoring.jiraalerts.dashboard.routine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import io.reflectoring.jiraalerts.application.testsetup.TestApplication;

@RunWith(MockitoJUnitRunner.class)
public class RoutineQueryDataProviderTest {

	private static final int ROWS_PER_PAGE = 5;
	private static final long USER_ID = 1337;
	private static final int ENTRY_COUNT = 4711;

	@Mock
	private RoutineQueryService routineQueryServiceMock;

	private RoutineQueryDataProvider testSubject;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
		new WicketTester(new TestApplication(this));
		testSubject = new RoutineQueryDataProvider(USER_ID, ROWS_PER_PAGE);
	}

	@Test
	public void sizeLetServiceCountEntries() throws Exception {
		testSubject.size();

		verify(routineQueryServiceMock).countRoutineQueriesByUserId(USER_ID);
	}

	@Test
	public void sizeReturnsValueFromService() throws Exception {
		when(routineQueryServiceMock.countRoutineQueriesByUserId(USER_ID)).thenReturn(ENTRY_COUNT);

		long size = testSubject.size();

		assertThat(size).isEqualTo(ENTRY_COUNT);
	}

	@Test
	public void iteratorLetServiceLoadEntries() throws Exception {
		testSubject.iterator(1, 5);

		verify(routineQueryServiceMock).getRoutineQueriesByUserId(eq(USER_ID), any(PageRequest.class));
	}

	@Test
	public void name() throws Exception {}

	@Test
	public void iteratorReturnsValueFromService() throws Exception {
		List<RoutineQueryDTO> queryDTOS = Arrays.asList(new RoutineQueryDTO(), new RoutineQueryDTO());
		when(routineQueryServiceMock.getRoutineQueriesByUserId(eq(USER_ID), any(PageRequest.class))).thenReturn(queryDTOS);

		Iterator<? extends RoutineQueryDTO> iterator = testSubject.iterator(1, 5);

		assertThat(iterator).hasSize(2);
	}

	@Test
	public void modelWrapsDTO() throws Exception {
		RoutineQueryDTO routineQueryDTO = new RoutineQueryDTO();

		IModel<RoutineQueryDTO> model = testSubject.model(routineQueryDTO);

		assertThat(model.getObject()).isSameAs(routineQueryDTO);
	}
}
