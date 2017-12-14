package io.reflectoring.jiraalerts.base;

import org.apache.wicket.feedback.FencedFeedbackPanel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.apache.wicket.validation.validator.StringValidator;
import org.junit.Before;
import org.junit.Test;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;

import io.reflectoring.jiraalerts.application.TestApplication;

public class LabeledTextfieldInputPanelTest {

	private static final String TEST_ERROR_MESSAGE = "input.StringValidator.range";
	private static final String TEST_VALUE_TO_SHORT = "test";
	private static final String TEST_VALUE_CORRECT = "testInputCorrect";

	private WicketTester wicketTester;

	private StringValidator stringValidator = new StringValidator(15, 20);

	@Before
	public void setUp() throws Exception {
		wicketTester = new WicketTester(new TestApplication(this));
	}

	@Test
	public void componentIsSuccessfullyRendered() {
		wicketTester.startComponentInPage(new LabeledTextfieldInputPanel("inputPanel", Model.of("labelValue"), Model.of(TEST_VALUE_CORRECT)));

		wicketTester.assertComponent("inputPanel:textInputForm", BootstrapForm.class);
		wicketTester.assertComponent("inputPanel:textInputForm:inputLabel", Label.class);
		wicketTester.assertComponent("inputPanel:textInputForm:input", TextField.class);
		wicketTester.assertComponent("inputPanel:textInputForm:feedback", FencedFeedbackPanel.class);
		wicketTester.assertNoErrorMessage();
	}

	@Test
	public void formSubmitValidatesInputFieldWithError() {
		wicketTester.startComponentInPage(
		        new LabeledTextfieldInputPanel("inputPanel", Model.of("labelValue"), Model.of(TEST_VALUE_TO_SHORT), stringValidator));
		FormTester formTester = wicketTester.newFormTester("inputPanel:textInputForm");

		formTester.submit();

		wicketTester.assertErrorMessages(TEST_ERROR_MESSAGE);
	}

	@Test
	public void formSubmitValidatesInputFieldSuccessfull() {
		wicketTester.startComponentInPage(
		        new LabeledTextfieldInputPanel("inputPanel", Model.of("labelValue"), Model.of(TEST_VALUE_CORRECT), stringValidator));
		FormTester formTester = wicketTester.newFormTester("inputPanel:textInputForm");

		formTester.submit();

		wicketTester.assertNoErrorMessage();
	}

	@Test
	public void formSubmitValidatesInputFieldWithRequiredParameter() {
		wicketTester.startComponentInPage(new LabeledTextfieldInputPanel("inputPanel", Model.of("labelValue"), Model.of(), true, stringValidator));
		FormTester formTester = wicketTester.newFormTester("inputPanel:textInputForm");

		formTester.submit();

		wicketTester.assertErrorMessages("input.Required");
	}
}
