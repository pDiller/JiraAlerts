package io.reflectoring.jiraalerts.dashboard;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

public class DeviceDashboardCardPanelTest {

	private WicketTester wicketTester = new WicketTester();

	@Test
	public void rendersSuccessful() throws Exception {
		wicketTester.startComponentInPage(new DeviceDashboardCardPanel("panel"));

		wicketTester.assertNoErrorMessage();
	}
}
