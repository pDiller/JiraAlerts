package io.reflectoring.jiraalerts.dashboard;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

public class ApplicationStatusDashboardCardPanelTest {

	private WicketTester wicketTester = new WicketTester();

	@Test
	public void rendersSuccessful() throws Exception {
		wicketTester.startComponentInPage(new ApplicationStatusDashboardCardPanel("panel", new Model<>()));

		wicketTester.assertNoErrorMessage();
	}
}
