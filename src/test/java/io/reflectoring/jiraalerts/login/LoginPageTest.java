package io.reflectoring.jiraalerts.login;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import io.reflectoring.jiraalerts.application.TestApplication;

public class LoginPageTest {

	private WicketTester wicketTester;

	@Before
	public void setUp() {
		wicketTester = new WicketTester(new TestApplication(this));
		wicketTester.startPage(LoginPage.class);
	}

	@Test
	public void basepageRendersSuccessfully() {
		wicketTester.assertRenderedPage(LoginPage.class);
	}

}
