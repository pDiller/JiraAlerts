package io.reflectoring.jiraalerts.common;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.apache.wicket.core.util.string.ComponentRenderer;
import org.apache.wicket.feedback.FencedFeedbackPanel;
import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

import io.reflectoring.jiraalerts.application.testsetup.TestApplication;

public class FormControlDropDownPanelTest {

	private static final String TEST_STRING = "Test1";
	private static final String TEST_LABEL = "Label";
	private static final List<String> TEST_CHOICES = asList("Test0", TEST_STRING, "Test2");
	private static final String FORM_TEST_MARKUP = "<html><body><form wicket:id=\"form\"><div wicket:id=\"panel\"></div></form></body></html>";

	private WicketTester wicketTester = new WicketTester(new TestApplication(this));
	private FormControlDropDownPanel<String> testSubject;

	@Test
	public void rendersSuccessful() throws Exception {
		String testString = "";
		testSubject = new FormControlDropDownPanel<String>("panel", Model.of(testString), new ListModel<>(TEST_CHOICES), new ChoiceRenderer<>(),
		        Model.of(TEST_LABEL));
		wicketTester.startComponentInPage(testSubject);

		wicketTester.assertComponent("panel:input", DropDownChoice.class);
		wicketTester.assertComponent("panel:label", SimpleFormComponentLabel.class);
		wicketTester.assertComponent("panel:feedback", FencedFeedbackPanel.class);
	}

	@Test
	public void formSubmitSetsDropDownValueInModel() throws Exception {
		startWithForm(false);

		FormTester formTester = wicketTester.newFormTester("form");
		formTester.select("panel:input", 1);
		formTester.submit();

		Object textFieldModelValue = wicketTester.getComponentFromLastRenderedPage("form:panel:input").getDefaultModelObject();
		assertThat(textFieldModelValue).isEqualTo(TEST_STRING);
	}

	@Test
	public void labelModelIsSetToLabel() throws Exception {
		String testString = "";
		wicketTester.startComponentInPage(new FormControlDropDownPanel<>("panel", Model.of(testString), new ListModel<>(TEST_CHOICES),
		        new ChoiceRenderer<>(), Model.of(TEST_LABEL)));

		wicketTester.assertModelValue("panel:label", TEST_LABEL);
	}

	@Test
	public void labelModelIsSetToPlaceholderAttribute() throws Exception {
		String testString = "";
		String panelMarkup = ComponentRenderer.renderComponent(new FormControlDropDownPanel<>("panel", Model.of(testString),
		        new ListModel<>(TEST_CHOICES), new ChoiceRenderer<>(), Model.of(TEST_LABEL))).toString();

		assertThat(panelMarkup).contains("placeholder=\"" + TEST_LABEL + "\"");
	}

	@Test
	public void textFieldIsRequiredWhenParameterIsTrue() throws Exception {
		startWithForm(true);

		FormTester formTester = wicketTester.newFormTester("form");
		formTester.submit();

		wicketTester.assertErrorMessages("input.Required");
	}

	@Test
	public void textFieldIsNotRequiredWhenParameterIsFalse() throws Exception {
		startWithForm(false);

		FormTester formTester = wicketTester.newFormTester("form");
		formTester.submit();

		wicketTester.assertNoErrorMessage();
	}

	private void startWithForm(boolean dropDownRequired) {
		String testString = "";
		Form<String> testForm = new Form<>("form");
		testForm.add(new FormControlDropDownPanel<>("panel", Model.of(testString), new ListModel<>(TEST_CHOICES), new ChoiceRenderer<>(),
		        Model.of(TEST_LABEL), dropDownRequired));
		wicketTester.startComponentInPage(testForm, Markup.of(FORM_TEST_MARKUP));
	}

	@Test
	public void getInputReturnsInputTextField() throws Exception {
		String testString = "";
		testSubject = new FormControlDropDownPanel<>("panel", Model.of(testString), new ListModel<>(TEST_CHOICES), new ChoiceRenderer<>(),
		        Model.of(TEST_LABEL));
		wicketTester.startComponentInPage(testSubject);

		assertThat(testSubject.getInput().getPath()).isEqualTo("0:panel:input");
	}
}
