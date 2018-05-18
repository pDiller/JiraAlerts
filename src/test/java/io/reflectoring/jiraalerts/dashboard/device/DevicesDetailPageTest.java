package io.reflectoring.jiraalerts.dashboard.device;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.mock.MockHomePage;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.application.testsetup.TestApplication;
import io.reflectoring.jiraalerts.application.testsetup.TestSession;

@RunWith(MockitoJUnitRunner.class)
public class DevicesDetailPageTest {

	@Mock
	private DeviceService deviceServiceMock;

	private WicketTester wicketTester;

	@Before
	public void setUp() throws Exception {
		wicketTester = new WicketTester(new TestApplication(this));
	}

	@Test
	public void rendersSuccessfully() throws Exception {
		TestSession.get().setSignedIn(true);
		TestSession.get().setRoles(new Roles("administrator"));
		wicketTester.startPage(new DevicesDetailPage());

		wicketTester.assertRenderedPage(DevicesDetailPage.class);
		wicketTester.assertComponent("deviceTable", DeviceDetailsTable.class);
	}

	@Test
	public void unauthorizedAccessRendersAccessDeniedPage() throws Exception {
		TestSession.get().setSignedIn(true);
		TestSession.get().setRoles(new Roles("tester"));
		wicketTester.startPage(DevicesDetailPage.class);

		wicketTester.assertRenderedPage(MockHomePage.class);
	}
}
