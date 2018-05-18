package io.reflectoring.jiraalerts.dashboard.routine;

import org.apache.wicket.markup.Markup;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.application.testsetup.TestApplication;

@RunWith(MockitoJUnitRunner.class)
public class RoutineQueryOverviewTableTest {

	private static final long USER_ID = 4711;

	@Mock
	private RoutineQueryService routineQueryServiceMock;

	private WicketTester wicketTester;

	@Before
	public void setUp() throws Exception {
		wicketTester = new WicketTester(new TestApplication(this));
		wicketTester.startComponentInPage(new RoutineQueryOverviewTable("table", USER_ID), Markup.of("<table wicket:id=table></table>"));
	}

	@Test
	public void rendersSuccessfull() throws Exception {
		wicketTester.assertNoErrorMessage();
	}
}
