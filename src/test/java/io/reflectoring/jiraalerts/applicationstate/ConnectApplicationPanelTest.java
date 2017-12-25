package io.reflectoring.jiraalerts.applicationstate;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.feedback.FencedFeedbackPanel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.application.TestApplication;
import io.reflectoring.jiraalerts.common.FormControlLabelPanel;
import io.reflectoring.jiraalerts.common.FormControlPasswordFieldPanel;

@RunWith(MockitoJUnitRunner.class)
public class ConnectApplicationPanelTest {

	private static final String TEST_URL = "https://jira.test.test";
	private static final String TEST_USERNAME = "tester@test.test";
	private static final String TEST_PASSWORD = "test123_";

	@Mock
	private SetupApplicationService setupApplicationServiceMock;

	private WicketTester wicketTester;
	private JiraLoginDTO jiraLoginDTO;

	@Before
	public void setUp() throws Exception {
		wicketTester = new WicketTester(new TestApplication(this));
		jiraLoginDTO = new JiraLoginDTO();
		jiraLoginDTO.setUrl(TEST_URL);
		jiraLoginDTO.setUsername(TEST_USERNAME);
		wicketTester.startComponentInPage(new ConnectApplicationPanel("panel", Model.of(jiraLoginDTO)));
	}

	@Test
	public void rendersSuccessFull() throws Exception {
		wicketTester.assertComponent("panel:globalFeedback", FencedFeedbackPanel.class);

		wicketTester.assertComponent("panel:setupForm", Form.class);
		wicketTester.assertComponent("panel:setupForm:urlLabelPanel", FormControlLabelPanel.class);
		wicketTester.assertComponent("panel:setupForm:usernameLabelPanel", FormControlLabelPanel.class);
		wicketTester.assertComponent("panel:setupForm:passwordInputPanel", FormControlPasswordFieldPanel.class);

		wicketTester.assertComponent("panel:setupForm:submitButton", AjaxFallbackButton.class);
	}

	@Test
	public void jiraUrlInputIsWired() throws Exception {
		wicketTester.assertModelValue("panel:setupForm:urlLabelPanel:input", TEST_URL);
	}

	@Test
	public void jiraUsernameInputIsWired() throws Exception {
		wicketTester.assertModelValue("panel:setupForm:usernameLabelPanel:input", TEST_USERNAME);
	}

	@Test
	public void valuesAreBoundToDTO() throws Exception {
		// For testing reasons PasswordTextField.setResetPassword has to be set to false, otherwise the modelObject of TextField is set to null
		// directly after formsubmit
		PasswordTextField passwordTextField = (PasswordTextField) wicketTester.getLastRenderedPage().get("panel:setupForm:passwordInputPanel:input");
		passwordTextField.setResetPassword(false);

		FormTester formTester = wicketTester.newFormTester("panel:setupForm");
		formTester.setValue("passwordInputPanel:input", TEST_PASSWORD);

		formTester.submit();

		assertThat(jiraLoginDTO.getUrl()).isEqualTo(TEST_URL);
		assertThat(jiraLoginDTO.getUsername()).isEqualTo(TEST_USERNAME);
		assertThat(jiraLoginDTO.getPassword()).isEqualTo(TEST_PASSWORD);
	}

	@Test
	public void submitFormWithSubmitButtonCallsService() throws Exception {
		FormTester formTester = wicketTester.newFormTester("panel:setupForm");
		formTester.setValue("passwordInputPanel:input", TEST_PASSWORD);

		formTester.submit("submitButton");

		verify(setupApplicationServiceMock).setupApplicaton(jiraLoginDTO);
	}

	@Test
	public void whenServiceCallThrowsMethodErrorIsShown() throws Exception {
		doThrow(new SetupApplicationFailedException("setup failed")).when(setupApplicationServiceMock).setupApplicaton(jiraLoginDTO);

		FormTester formTester = wicketTester.newFormTester("panel:setupForm");
		formTester.setValue("passwordInputPanel:input", TEST_PASSWORD);

		formTester.submit("submitButton");

		wicketTester.assertErrorMessages("setup.application.failed");
	}
}
