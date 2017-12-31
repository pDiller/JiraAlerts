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
public class RoutineQueryTablePanelTest {

	private static final long USER_ID = 1337;

	@Mock
	private RoutineQueryService routineQueryServiceMock;

	private WicketTester wicketTester;

	@Before
	public void setUp() throws Exception {
		wicketTester = new WicketTester(new TestApplication(this));
		wicketTester.startComponentInPage(new RoutineQueryTablePanel("panel", USER_ID));
	}

	@Test
	public void rendersSuccessfull() throws Exception {
		wicketTester.assertComponent("panel:routineQueryTable", RoutineQueryTable.class);
	}
}
