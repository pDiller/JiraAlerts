package io.reflectoring.jiraalerts.dashboard;

import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

public class AbstractDashboardCardPanelTest {

	private WicketTester wicketTester = new WicketTester();

	@Test
	public void rendersSuccessful() throws Exception {
		wicketTester.startComponentInPage(new AbstractDashboardCardPanel<String>("panel", Model.of("")) {});

		wicketTester.assertNoErrorMessage();
	}
}
