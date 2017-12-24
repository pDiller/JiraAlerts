package io.reflectoring.jiraalerts.dashboard;

import static io.reflectoring.jiraalerts.application.ApplicationState.ACTIVE;
import static org.mockito.Mockito.when;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.mock.MockHomePage;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.application.ApplicationStateService;
import io.reflectoring.jiraalerts.application.TestApplication;
import io.reflectoring.jiraalerts.application.TestSession;
import io.reflectoring.jiraalerts.applicationstate.JiraConnectionRepository;

@RunWith(MockitoJUnitRunner.class)
public class DashboardPageTest {

	@Mock
	private ApplicationStateService applicationStateServiceMock;

	@Mock
	private JiraConnectionRepository jiraConnectionRepositoryMock;

	private WicketTester wicketTester;

	@Before
	public void setUp() throws Exception {
		when(applicationStateServiceMock.getApplicationState()).thenReturn(ACTIVE);
		wicketTester = new WicketTester(new TestApplication(this));
	}

	@Test
	public void rendersSuccessfull() throws Exception {
		TestSession.get().setSignedIn(true);
		TestSession.get().setRoles(new Roles("administrator"));
		wicketTester.startPage(new DashboardPage());

		wicketTester.assertRenderedPage(DashboardPage.class);
		wicketTester.assertComponent("routinePanel", RoutineDashboardCardPanel.class);
		wicketTester.assertComponent("profilePanel", ProfileDashboardCardPanel.class);
		wicketTester.assertComponent("userManagementPanel", UserManagementDashboardCardPanel.class);
		wicketTester.assertComponent("applicationStatePanel", ApplicationStateDashboardCardPanel.class);
	}

	@Test
	public void unauthorizedAccessRendersAccessDeniedPage() throws Exception {
		TestSession.get().setSignedIn(true);
		TestSession.get().setRoles(new Roles("tester"));
		wicketTester.startPage(DashboardPage.class);

		wicketTester.assertRenderedPage(MockHomePage.class);
	}
}
