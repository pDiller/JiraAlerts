package io.reflectoring.jiraalerts.base;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import de.agilecoders.wicket.core.markup.html.bootstrap.heading.Heading;

import io.reflectoring.jiraalerts.application.TestApplication;

public class BasePageTest {

	private WicketTester wicketTester;

	@Before
	public void setUp() {
		wicketTester = new WicketTester(new TestApplication(this));
		wicketTester.startPage(new BasePage() {});
	}

	@Test
	public void basepageRendersSuccessfully() {
		wicketTester.assertRenderedPage(BasePage.class);
	}

	@Test
	public void basepageContainsNavbar() {
		wicketTester.assertComponent("navbarHeaderPanel", NavbarHeaderPanel.class);
	}

	@Test
	public void basepageContainsPageHeading() throws Exception {
		wicketTester.assertComponent("pageHeading", Heading.class);
	}
}
