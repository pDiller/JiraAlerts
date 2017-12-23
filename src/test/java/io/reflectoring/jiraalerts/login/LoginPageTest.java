package io.reflectoring.jiraalerts.login;

import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.feedback.FencedFeedbackPanel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import io.reflectoring.jiraalerts.application.TestApplication;
import io.reflectoring.jiraalerts.application.TestSession;
import io.reflectoring.jiraalerts.common.FormControlPasswordFieldPanel;
import io.reflectoring.jiraalerts.common.FormControlTextFieldPanel;
import io.reflectoring.jiraalerts.dashboard.DashboardPage;

public class LoginPageTest {

	private static final String TEST_PASSWORD = "Password";
	private static final String TEST_USERNAME = "Username";

	private WicketTester wicketTester;

	@Before
	public void setUp() {
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
