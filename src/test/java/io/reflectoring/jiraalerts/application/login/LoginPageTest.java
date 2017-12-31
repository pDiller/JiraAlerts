package io.reflectoring.jiraalerts.application.login;

import static io.reflectoring.jiraalerts.application.state.ApplicationState.ACTIVE;
import static org.mockito.Mockito.when;

import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.feedback.FencedFeedbackPanel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.application.state.ApplicationStateService;
import io.reflectoring.jiraalerts.application.testsetup.TestApplication;
import io.reflectoring.jiraalerts.application.testsetup.TestSession;
import io.reflectoring.jiraalerts.common.FormControlPasswordFieldPanel;
import io.reflectoring.jiraalerts.common.FormControlTextFieldPanel;
import io.reflectoring.jiraalerts.dashboard.DashboardPage;
import io.reflectoring.jiraalerts.dashboard.applicationstate.JiraConnectionRepository;
import io.reflectoring.jiraalerts.dashboard.routine.RoutineQueryService;

@RunWith(MockitoJUnitRunner.class)
public class LoginPageTest {

	private static final String TEST_PASSWORD = "Password";
	private static final String TEST_USERNAME = "Username";

	@Mock
	private ApplicationStateService applicationStateServiceMock;

	@Mock
	private JiraConnectionRepository jiraConnectionRepositoryMock;

	@Mock
	private RoutineQueryService routineQueryServiceMock;

	private WicketTester wicketTester;

	@Before
	public void setUp() {
		when(applicationStateServiceMock.getApplicationState()).thenReturn(ACTIVE);
		wicketTester = new WicketTester(new TestApplication(this));
		wicketTester.startPage(LoginPage.class);
	}

	@Test
	public void basepageRendersSuccessfully() {
		wicketTester.assertComponent("loginForm", Form.class);
		wicketTester.assertComponent("loginFeedbackPanel", FencedFeedbackPanel.class);
		wicketTester.assertComponent("loginForm:loginUsernamePanel", FormControlTextFieldPanel.class);
		wicketTester.assertComponent("loginForm:loginPasswordPanel", FormControlPasswordFieldPanel.class);
		wicketTester.assertComponent("loginForm:loginButton", AjaxFallbackButton.class);

		wicketTester.assertRenderedPage(LoginPage.class);
	}

	@Test
	public void usernameIsRequired() throws Exception {
		FormTester formTester = wicketTester.newFormTester("loginForm");
		formTester.setValue("loginPasswordPanel:input", TEST_PASSWORD);

		formTester.submit();

		wicketTester.assertErrorMessages("input.Required");
	}

	@Test
	public void loginButtonSubmitsForm() throws Exception {
		TestSession.get().setSignedIn(true);
		TestSession.get().setRoles(new Roles("administrator"));
		FormTester formTester = wicketTester.newFormTester("loginForm");
		formTester.setValue("loginUsernamePanel:input", TEST_USERNAME);
		formTester.setValue("loginPasswordPanel:input", TEST_PASSWORD);

		formTester.submit("loginButton");

		wicketTester.assertRenderedPage(DashboardPage.class);
	}

	@Test
	public void loginFailedshowsErrorMessage() throws Exception {
		TestSession.get().setSignedIn(false);
		TestSession.get().setRoles(new Roles("administrator"));
		FormTester formTester = wicketTester.newFormTester("loginForm");
		formTester.setValue("loginUsernamePanel:input", TEST_USERNAME);
		formTester.setValue("loginPasswordPanel:input", TEST_PASSWORD);

		formTester.submit("loginButton");

		wicketTester.assertErrorMessages("login.failed.text");
	}
}
