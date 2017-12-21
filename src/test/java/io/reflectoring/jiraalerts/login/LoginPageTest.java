package io.reflectoring.jiraalerts.login;

import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.feedback.FencedFeedbackPanel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import io.reflectoring.jiraalerts.application.TestApplication;
import io.reflectoring.jiraalerts.application.TestSession;
import io.reflectoring.jiraalerts.home.HomePage;

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
		wicketTester.assertComponent("loginFeedbackPanel", FencedFeedbackPanel.class);
		wicketTester.assertComponent("loginForm", Form.class);
		wicketTester.assertComponent("loginForm:usernameLabel", SimpleFormComponentLabel.class);
		wicketTester.assertComponent("loginForm:usernameInput", TextField.class);
		wicketTester.assertComponent("loginForm:usernameFeedbackPanel", FencedFeedbackPanel.class);

		wicketTester.assertComponent("loginForm:passwordLabel", SimpleFormComponentLabel.class);
		wicketTester.assertComponent("loginForm:passwordInput", PasswordTextField.class);
		wicketTester.assertComponent("loginForm:passwordFeedbackPanel", FencedFeedbackPanel.class);

		wicketTester.assertComponent("loginForm:loginButton", AjaxFallbackButton.class);

		wicketTester.assertRenderedPage(LoginPage.class);
	}

	@Test
	public void usernameIsRequired() throws Exception {
		FormTester formTester = wicketTester.newFormTester("loginForm");
		formTester.setValue("passwordInput", TEST_PASSWORD);

		formTester.submit();

		wicketTester.assertErrorMessages("usernameInput.Required");
	}

	@Test
	public void passwordIsRequired() throws Exception {
		FormTester formTester = wicketTester.newFormTester("loginForm");
		formTester.setValue("usernameInput", TEST_USERNAME);

		formTester.submit();

		wicketTester.assertErrorMessages("passwordInput.Required");
	}

	@Test
	public void loginButtonSubmitsForm() throws Exception {
		TestSession.get().setSignedIn(true);
		TestSession.get().setRoles(new Roles("administrator"));
		FormTester formTester = wicketTester.newFormTester("loginForm");
		formTester.setValue("usernameInput", TEST_USERNAME);
		formTester.setValue("passwordInput", TEST_PASSWORD);

		formTester.submit("loginButton");

		wicketTester.assertRenderedPage(HomePage.class);
	}

	@Test
	public void loginFailedshowsErrorMessage() throws Exception {
		TestSession.get().setSignedIn(false);
		TestSession.get().setRoles(new Roles("administrator"));
		FormTester formTester = wicketTester.newFormTester("loginForm");
		formTester.setValue("usernameInput", TEST_USERNAME);
		formTester.setValue("passwordInput", TEST_PASSWORD);

		formTester.submit("loginButton");

		wicketTester.assertErrorMessages("login.failed.text");
	}
}
