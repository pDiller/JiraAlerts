package io.reflectoring.jiraalerts.base;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import io.reflectoring.jiraalerts.application.TestApplication;

public class LoggedInBasePageTest {

	private WicketTester wicketTester;

	@Before
	public void setUp() {
		wicketTester = new WicketTester(new TestApplication(this));
		wicketTester.startPage(LoggedInBasePage.class);
	}

	@Test
	public void basepageRendersSuccessfully() {
		wicketTester.assertRenderedPage(LoggedInBasePage.class);
	}

	@Test
	public void basepageContainsNavbar() {
		wicketTester.assertComponent("loggedInNavbarHeaderPanel", LoggedInNavbarHeaderPanel.class);
	}
}
