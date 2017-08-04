package io.reflectoring.jiraalerts.base.components;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.StringValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;

import io.reflectoring.jiraalerts.base.wickettests.TestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfiguration.class })
public class LabeledTextfieldInputPanelTest {

	private static final String TEST_ERROR_MESSAGE = "input.StringValidator.range";
	private static final String TEST_VALUE_TO_SHORT = "test";
	private static final String TEST_VALUE_CORRECT = "testInputCorrect";

	@Autowired
	private WicketTester wicketTester;
	private StringValidator stringValidator = new StringValidator(15, 20);;

	@Test
	public void componentIsSuccessfullyRendered() {
		IValidator<String>[] validators = new IValidator[] {};
		wicketTester
		        .startComponentInPage(new LabeledTextfieldInputPanel("inputPanel", Model.of("labelValue"), Model.of(TEST_VALUE_CORRECT), validators));

		wicketTester.assertComponent("inputPanel:textInputForm", BootstrapForm.class);
		wicketTester.assertComponent("inputPanel:textInputForm:inputLabel", Label.class);
		wicketTester.assertComponent("inputPanel:textInputForm:input", TextField.class);
		wicketTester.assertComponent("inputPanel:textInputForm:feedback", ComponentFeedbackPanel.class);
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
	public void changeEventValidatesInputFieldWithError() {
		wicketTester.startComponentInPage(new LabeledTextfieldInputPanel("inputPanel", Model.of("labelValue"), new Model<>(), stringValidator));
		FormTester formTester = wicketTester.newFormTester("inputPanel:textInputForm");
		formTester.setValue("input", TEST_VALUE_TO_SHORT);

		wicketTester.executeAjaxEvent("inputPanel:textInputForm:input", "change");

		wicketTester.assertErrorMessages(TEST_ERROR_MESSAGE);
	}

	@Test
	public void changeEventValidatesInputFieldSuccessfull() {
		wicketTester.startComponentInPage(new LabeledTextfieldInputPanel("inputPanel", Model.of("labelValue"), new Model<>(), stringValidator));
		FormTester formTester = wicketTester.newFormTester("inputPanel:textInputForm");
		formTester.setValue("input", TEST_VALUE_CORRECT);

		wicketTester.executeAjaxEvent("inputPanel:textInputForm:input", "change");

		wicketTester.assertNoErrorMessage();
	}
}
