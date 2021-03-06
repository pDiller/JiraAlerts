package io.reflectoring.jiraalerts.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.wicket.core.util.string.ComponentRenderer;
import org.apache.wicket.feedback.FencedFeedbackPanel;
import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

import io.reflectoring.jiraalerts.application.testsetup.TestApplication;

public class FormControlPasswordFieldPanelTest {

	private static final String TEST_STRING = "Test";
	private static final String TEST_LABEL = "Label";
	public static final String FORM_TEST_MARKUP = "<html><body><form wicket:id=\"form\"><div wicket:id=\"panel\"></div></form></body></html>";

	private WicketTester wicketTester = new WicketTester(new TestApplication(this));

	@Test
	public void rendersSuccessful() throws Exception {
		String testString = "";
		wicketTester.startComponentInPage(new FormControlPasswordFieldPanel("panel", Model.of(testString), Model.of(TEST_LABEL)));

		wicketTester.assertComponent("panel:input", TextField.class);
		wicketTester.assertComponent("panel:label", SimpleFormComponentLabel.class);
		wicketTester.assertComponent("panel:feedback", FencedFeedbackPanel.class);
	}

	@Test
	public void formSubmitSetsTextfieldsValueInModel() throws Exception {
		startWithForm();

		// For testing reasons PasswordTextField.setResetPassword has to be set to false, otherwise the modelObject of TextField is set to null
		// directly after formsubmit
		PasswordTextField passwordTextField = (PasswordTextField) wicketTester.getLastRenderedPage().get("form:panel:input");
		passwordTextField.setResetPassword(false);

		FormTester formTester = wicketTester.newFormTester("form");
		formTester.setValue("panel:input", TEST_STRING);
		formTester.submit();

		Object textFieldModelValue = wicketTester.getComponentFromLastRenderedPage("form:panel:input").getDefaultModelObject();
		assertThat(textFieldModelValue).isEqualTo(TEST_STRING);
	}

	@Test
	public void labelModelIsSetToLabel() throws Exception {
		String testString = "";
		wicketTester.startComponentInPage(new FormControlPasswordFieldPanel("panel", Model.of(testString), Model.of(TEST_LABEL)));

		wicketTester.assertModelValue("panel:label", TEST_LABEL);
	}

	@Test
	public void labelModelIsSetToPlaceholderAttribute() throws Exception {
		String testString = "";
		String panelMarkup = ComponentRenderer.renderComponent(new FormControlPasswordFieldPanel("panel", Model.of(testString), Model.of(TEST_LABEL)))
		        .toString();

		assertThat(panelMarkup).contains("placeholder=\"" + TEST_LABEL + "\"");
	}

	@Test
	public void textFieldIsRequiredWhenParameterIsTrue() throws Exception {
		startWithForm();

		FormTester formTester = wicketTester.newFormTester("form");
		formTester.submit();

		wicketTester.assertErrorMessages("input.Required");
	}

	private void startWithForm() {
		String testString = "";
		Form<String> testForm = new Form<>("form");
		testForm.add(new FormControlPasswordFieldPanel("panel", Model.of(testString), Model.of(TEST_LABEL)));
		wicketTester.startComponentInPage(testForm, Markup.of(FORM_TEST_MARKUP));
	}
}
