package io.reflectoring.jiraalerts.dashboard;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.application.testsetup.TestApplication;
import io.reflectoring.jiraalerts.dashboard.device.DeviceOverviewTable;
import io.reflectoring.jiraalerts.dashboard.device.DeviceService;

@RunWith(MockitoJUnitRunner.class)
public class DeviceDashboardCardPanelTest {

	@Mock
	private DeviceService deviceServiceMock;

	private WicketTester wicketTester;

	@Before
	public void setUp() throws Exception {
		wicketTester = new WicketTester(new TestApplication(this));
	}

	@Test
	public void rendersSuccessful() throws Exception {
		wicketTester.startComponentInPage(new DeviceDashboardCardPanel("panel"));

		wicketTester.assertComponent("panel:deviceTable", DeviceOverviewTable.class);
		wicketTester.assertComponent("panel:showAllDevicesLink", BookmarkablePageLink.class);
	}

}
