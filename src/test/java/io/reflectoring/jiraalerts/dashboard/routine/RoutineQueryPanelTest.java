package io.reflectoring.jiraalerts.dashboard.routine;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.application.testsetup.TestApplication;
import io.reflectoring.jiraalerts.common.FormControlTextFieldPanel;

@RunWith(MockitoJUnitRunner.class)
public class RoutineQueryPanelTest {

	private static final String ROUTINE_NAME = "RoutineName";
	private static final String ROUTINE_JQL = "RoutineJql";
	private static final String ROUTINE_MINUTES = "4";

	private WicketTester wicketTester = new WicketTester(new TestApplication(this));
	private RoutineQueryDTO routineQueryDTO;

	@Before
	public void setUp() throws Exception {
		routineQueryDTO = new RoutineQueryDTO();
		wicketTester.startComponentInPage(new RoutineQueryPanel("panel", new Model<>(routineQueryDTO)));
	}

	@Test
	public void rendersSuccessfull() throws Exception {
		wicketTester.assertComponent("panel:routineForm", Form.class);
		wicketTester.assertComponent("panel:routineForm:routineNamePanel", FormControlTextFieldPanel.class);
		wicketTester.assertComponent("panel:routineForm:routineJqlPanel", FormControlTextFieldPanel.class);
		wicketTester.assertComponent("panel:routineForm:routineMinutesPanel", FormControlTextFieldPanel.class);
	}

	@Test
	public void routineNameIsRequired() throws Exception {
		FormTester formTester = wicketTester.newFormTester("panel:routineForm");

		formTester.setValue("routineJqlPanel:input", ROUTINE_JQL);
		formTester.setValue("routineMinutesPanel:input", ROUTINE_MINUTES);

		formTester.submit();

		wicketTester.assertErrorMessages("input.Required");
	}

	@Test
	public void routineJqlIsRequired() throws Exception {
		FormTester formTester = wicketTester.newFormTester("panel:routineForm");

		formTester.setValue("routineNamePanel:input", ROUTINE_NAME);
		formTester.setValue("routineMinutesPanel:input", ROUTINE_MINUTES);

		formTester.submit();

		wicketTester.assertErrorMessages("input.Required");
	}

	@Test
	public void setRoutineMinutesToZeroShowsErrorMessage() throws Exception {
		FormTester formTester = wicketTester.newFormTester("panel:routineForm");

		formTester.setValue("routineNamePanel:input", ROUTINE_NAME);
		formTester.setValue("routineJqlPanel:input", ROUTINE_JQL);
		formTester.setValue("routineMinutesPanel:input", "0");

		formTester.submit();

		wicketTester.assertErrorMessages("input.RangeValidator.range");
	}

	@Test
	public void setRoutineMinutesTo1441ShowsErrorMessage() throws Exception {
		FormTester formTester = wicketTester.newFormTester("panel:routineForm");

		formTester.setValue("routineNamePanel:input", ROUTINE_NAME);
		formTester.setValue("routineJqlPanel:input", ROUTINE_JQL);
		formTester.setValue("routineMinutesPanel:input", "1441");

		formTester.submit();

		wicketTester.assertErrorMessages("input.RangeValidator.range");
	}

	@Test
	public void setRoutineMinutesToAStringShowsErrorMessage() throws Exception {
		FormTester formTester = wicketTester.newFormTester("panel:routineForm");

		formTester.setValue("routineNamePanel:input", ROUTINE_NAME);
		formTester.setValue("routineJqlPanel:input", ROUTINE_JQL);
		formTester.setValue("routineMinutesPanel:input", ROUTINE_NAME);

		formTester.submit();

		wicketTester.assertErrorMessages("input.IConverter.int");
	}

	@Test
	public void submitFormSetsValueToDTO() throws Exception {
		FormTester formTester = wicketTester.newFormTester("panel:routineForm");

		formTester.setValue("routineNamePanel:input", ROUTINE_NAME);
		formTester.setValue("routineJqlPanel:input", ROUTINE_JQL);
		formTester.setValue("routineMinutesPanel:input", ROUTINE_MINUTES);

		formTester.submit();

		SoftAssertions softAssertions = new SoftAssertions();
		softAssertions.assertThat(routineQueryDTO.getName()).isEqualTo(ROUTINE_NAME);
		softAssertions.assertThat(routineQueryDTO.getJqlString()).isEqualTo(ROUTINE_JQL);
		softAssertions.assertThat(routineQueryDTO.getMinutesForRecognition()).isEqualTo(Integer.parseInt(ROUTINE_MINUTES));
		softAssertions.assertAll();
	}
}
