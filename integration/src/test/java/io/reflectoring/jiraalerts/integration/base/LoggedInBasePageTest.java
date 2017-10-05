package io.reflectoring.jiraalerts.integration.base;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.reflectoring.jiraalerts.integration.wickettests.IntegrationTestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
public class LoggedInBasePageTest {

	@Autowired
	private WicketTester wicketTester;

	@Before
	public void setUp() {
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
