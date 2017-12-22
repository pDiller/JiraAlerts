package io.reflectoring.jiraalerts.dashboard;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

import io.reflectoring.jiraalerts.application.TestApplication;
import io.reflectoring.jiraalerts.application.TestSession;

public class DashboardPageTest {

	private WicketTester wicketTester = new WicketTester(new TestApplication(this));

	@Test
	public void rendersSuccessfull() throws Exception {
		TestSession.get().setSignedIn(true);
		TestSession.get().setRoles(new Roles("administrator"));
		wicketTester.startPage(DashboardPage.class);

		wicketTester.assertRenderedPage(DashboardPage.class);
		wicketTester.assertComponent("routinePanel", RoutineDashboardCardPanel.class);
		wicketTester.assertComponent("profilePanel", ProfileDashboardCardPanel.class);
		wicketTester.assertComponent("userManagementPanel", UserManagementDashboardCardPanel.class);
		wicketTester.assertComponent("applicationStatusPanel", ApplicationStatusDashboardCardPanel.class);
	}
}
