package io.reflectoring.jiraalerts.admin.applicationactivation;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import io.reflectoring.jiraalerts.application.TestApplication;

public class ActivateApplicationPageTest {

	private WicketTester wicketTester;

	@Before
	public void setUp() {
		wicketTester = new WicketTester(new TestApplication(this));
		wicketTester.startPage(ActivateApplicationPage.class);
	}

	@Test
	public void rendersSuccessfully() throws Exception {
		wicketTester.assertRenderedPage(ActivateApplicationPage.class);
		wicketTester.assertComponent("activateApplicationPanel", ActivateApplicationPanel.class);
	}
}
