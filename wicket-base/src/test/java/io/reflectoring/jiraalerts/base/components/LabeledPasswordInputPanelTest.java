package io.reflectoring.jiraalerts.base.components;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;

import io.reflectoring.jiraalerts.base.wickettests.TestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfiguration.class })
public class LabeledPasswordInputPanelTest {

	private static final String TEST_ERROR_REQUIRED = "password.Required";
	private static final String TEST_VALUE_CORRECT = "testInputCorrect";

	@Autowired
	private WicketTester wicketTester;

	@Test
	public void componentIsSuccessfullyRendered() {
		wicketTester.startComponentInPage(new LabeledPasswordInputPanel("inputPanel", Model.of("labelValue"), Model.of(TEST_VALUE_CORRECT)));

		wicketTester.assertComponent("inputPanel:textInputForm", BootstrapForm.class);
		wicketTester.assertComponent("inputPanel:textInputForm:inputLabel", Label.class);
		wicketTester.assertComponent("inputPanel:textInputForm:password", PasswordTextField.class);
		wicketTester.assertComponent("inputPanel:textInputForm:feedback", ComponentFeedbackPanel.class);
		wicketTester.assertNoErrorMessage();
	}

	@Test
	public void formSubmitValidatesPasswordFieldWithRequiredError() {
		wicketTester.startComponentInPage(new LabeledPasswordInputPanel("inputPanel", Model.of("labelValue"), Model.of("")));
		FormTester formTester = wicketTester.newFormTester("inputPanel:textInputForm");

		formTester.submit();

		wicketTester.assertErrorMessages(TEST_ERROR_REQUIRED);
	}

	@Test
	public void formSubmitValidatesInputFieldSuccessfull() {
		wicketTester.startComponentInPage(new LabeledPasswordInputPanel("inputPanel", Model.of("labelValue"), Model.of("")));
		FormTester formTester = wicketTester.newFormTester("inputPanel:textInputForm");
		formTester.setValue("password", TEST_VALUE_CORRECT);
		formTester.submit();

		wicketTester.assertNoErrorMessage();
	}

	@Test
	public void formSubmitValidatesPasswordFieldNotSuccessfully() {
		wicketTester.startComponentInPage(new LabeledPasswordInputPanel("inputPanel", Model.of("labelValue"), Model.of("")));
		FormTester formTester = wicketTester.newFormTester("inputPanel:textInputForm");

		formTester.submit();

		wicketTester.assertErrorMessages(TEST_ERROR_REQUIRED);
	}

	@Test
	public void changeEventValidatesPasswordFieldSuccessfull() {
		wicketTester.startComponentInPage(new LabeledPasswordInputPanel("inputPanel", Model.of("labelValue"), new Model<>()));
		FormTester formTester = wicketTester.newFormTester("inputPanel:textInputForm");
		formTester.setValue("password", TEST_VALUE_CORRECT);

		wicketTester.executeAjaxEvent("inputPanel:textInputForm:password", "change");

		wicketTester.assertNoErrorMessage();
	}
}
