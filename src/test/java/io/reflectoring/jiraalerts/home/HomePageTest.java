package io.reflectoring.jiraalerts.home;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

import io.reflectoring.jiraalerts.application.TestApplication;
import io.reflectoring.jiraalerts.application.TestSession;

public class HomePageTest {

	private WicketTester wicketTester = new WicketTester(new TestApplication(this));

	@Test
	public void rendersSuccessfull() throws Exception {
		TestSession.get().setSignedIn(true);
		TestSession.get().setRoles(new Roles("administrator"));
		wicketTester.startPage(HomePage.class);

		wicketTester.assertRenderedPage(HomePage.class);
	}
}
