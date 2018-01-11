package io.reflectoring.jiraalerts.dashboard.routine;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import io.reflectoring.jiraalerts.application.testsetup.TestApplication;
import io.reflectoring.jiraalerts.application.testsetup.TestSession;

public class CreateRoutineQueryPageTest {

	private WicketTester wicketTester;

	@Before
	public void setUp() throws Exception {
		wicketTester = new WicketTester(new TestApplication(this));
		TestSession.get().setRoles(new Roles("administrator"));
		wicketTester.startPage(new CreateRoutineQueryPage());
	}

	@Test
	public void rendersSuccessfull() throws Exception {
		wicketTester.assertComponent("createRoutineForm", Form.class);
		wicketTester.assertComponent("createRoutineForm:routineQueryPanel", RoutineQueryPanel.class);
		wicketTester.assertComponent("createRoutineForm:submitButton", AjaxFallbackButton.class);
	}

	@Test(expected = RestartResponseAtInterceptPageException.class)
	public void redirectsToLoginPageWhenRoleIsNotCorrect() throws Exception {
		TestSession.get().setRoles(new Roles("noRole"));
		wicketTester.startPage(new CreateRoutineQueryPage());
	}
}
