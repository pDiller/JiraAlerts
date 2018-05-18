package io.reflectoring.jiraalerts.dashboard.routine;

import static org.mockito.Mockito.when;

import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.application.testsetup.TestApplication;
import io.reflectoring.jiraalerts.common.FormControlTextFieldPanel;

@RunWith(MockitoJUnitRunner.class)
public class RoutineQueryPanelTest {

	private static final String ROUTINE_NAME = "RoutineName";
	private static final String ROUTINE_JQL = "RoutineJql";
	private static final String ROUTINE_MINUTES = "4";

	@Mock
	private RoutineQueryService routineQueryServiceMock;

	private WicketTester wicketTester;
	private RoutineQueryDTO routineQueryDTO;

	@Before
	public void setUp() throws Exception {
		wicketTester = new WicketTester(new TestApplication(this));
		routineQueryDTO = new RoutineQueryDTO();
		wicketTester.startComponentInPage(new RoutineQueryPanel("panel", new Model<>(routineQueryDTO)));
	}

	@Test
	public void rendersSuccessfull() throws Exception {
		wicketTester.assertComponent("panel:routineForm", Form.class);
		wicketTester.assertComponent("panel:routineForm:routineNamePanel", FormControlTextFieldPanel.class);
		wicketTester.assertComponent("panel:routineForm:jqlForm:routineJqlPanel", FormControlTextFieldPanel.class);
		wicketTester.assertComponent("panel:routineForm:jqlForm", Form.class);
		wicketTester.assertInvisible("panel:routineForm:jqlForm:successIcon");
		wicketTester.assertInvisible("panel:routineForm:jqlForm:failIcon");
		wicketTester.assertComponent("panel:routineForm:jqlForm:checkJqlLink", AjaxFallbackButton.class);
		wicketTester.assertComponent("panel:routineForm:routineMinutesPanel", FormControlTextFieldPanel.class);
	}

	@Test
	public void routineNameIsRequired() throws Exception {
		FormTester formTester = wicketTester.newFormTester("panel:routineForm");

		formTester.setValue("jqlForm:routineJqlPanel:input", ROUTINE_JQL);
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
		formTester.setValue("jqlForm:routineJqlPanel:input", ROUTINE_JQL);
		formTester.setValue("routineMinutesPanel:input", "0");

		formTester.submit();

		wicketTester.assertErrorMessages("input.RangeValidator.range");
	}

	@Test
	public void setRoutineMinutesTo1441ShowsErrorMessage() throws Exception {
		FormTester formTester = wicketTester.newFormTester("panel:routineForm");

		formTester.setValue("routineNamePanel:input", ROUTINE_NAME);
		formTester.setValue("jqlForm:routineJqlPanel:input", ROUTINE_JQL);
		formTester.setValue("routineMinutesPanel:input", "61");

		formTester.submit();

		wicketTester.assertErrorMessages("input.RangeValidator.range");
	}

	@Test
	public void setRoutineMinutesToAStringShowsErrorMessage() throws Exception {
		FormTester formTester = wicketTester.newFormTester("panel:routineForm");

		formTester.setValue("routineNamePanel:input", ROUTINE_NAME);
		formTester.setValue("jqlForm:routineJqlPanel:input", ROUTINE_JQL);
		formTester.setValue("routineMinutesPanel:input", ROUTINE_NAME);

		formTester.submit();

		wicketTester.assertErrorMessages("input.IConverter.int");
	}

	@Test
	public void submitFormSetsValueToDTO() throws Exception {
		FormTester formTester = wicketTester.newFormTester("panel:routineForm");

		formTester.setValue("routineNamePanel:input", ROUTINE_NAME);
		formTester.setValue("jqlForm:routineJqlPanel:input", ROUTINE_JQL);
		formTester.setValue("routineMinutesPanel:input", ROUTINE_MINUTES);

		formTester.submit();

		SoftAssertions softAssertions = new SoftAssertions();
		softAssertions.assertThat(routineQueryDTO.getName()).isEqualTo(ROUTINE_NAME);
		softAssertions.assertThat(routineQueryDTO.getJqlString()).isEqualTo(ROUTINE_JQL);
		softAssertions.assertThat(routineQueryDTO.getMinutesForRecognition()).isEqualTo(Integer.parseInt(ROUTINE_MINUTES));
		softAssertions.assertAll();
	}

	@Test
	public void positiveJqlCheckShowsSuccessIcon() throws Exception {
		when(routineQueryServiceMock.checkJql(ROUTINE_JQL)).thenReturn(true);
		wicketTester.startComponentInPage(new RoutineQueryPanel("panel", new Model<>(routineQueryDTO)));
		FormTester formTester = wicketTester.newFormTester("panel:routineForm");
		formTester.setValue("jqlForm:routineJqlPanel:input", ROUTINE_JQL);

		formTester.submit("jqlForm:checkJqlLink");

		wicketTester.assertVisible("panel:routineForm:jqlForm:successIcon");
		wicketTester.assertInvisible("panel:routineForm:jqlForm:failIcon");
	}

	@Test
	public void negativeJqlCheckShowsFailIcon() throws Exception {
		when(routineQueryServiceMock.checkJql(ROUTINE_JQL)).thenReturn(false);
		wicketTester.startComponentInPage(new RoutineQueryPanel("panel", new Model<>(routineQueryDTO)));
		FormTester formTester = wicketTester.newFormTester("panel:routineForm");
		formTester.setValue("jqlForm:routineJqlPanel:input", ROUTINE_JQL);

		formTester.submit("jqlForm:checkJqlLink");

		wicketTester.assertVisible("panel:routineForm:jqlForm:failIcon");
		wicketTester.assertInvisible("panel:routineForm:jqlForm:successIcon");
	}
}
