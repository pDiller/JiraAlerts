package io.reflectoring.jiraalerts.integration.base;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.agilecoders.wicket.core.markup.html.bootstrap.heading.Heading;

import io.reflectoring.jiraalerts.integration.wickettests.IntegrationTestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
public class BasePageTest {

	@Autowired
	private WicketTester wicketTester;

	@Before
	public void setUp() {
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
