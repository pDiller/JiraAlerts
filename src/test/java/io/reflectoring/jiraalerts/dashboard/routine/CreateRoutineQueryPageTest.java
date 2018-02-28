package io.reflectoring.jiraalerts.dashboard.routine;

import static io.reflectoring.jiraalerts.dashboard.routine.RoutineQueryState.ACTIVE;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.application.testsetup.TestApplication;
import io.reflectoring.jiraalerts.application.testsetup.TestSession;

@RunWith(MockitoJUnitRunner.class)
public class CreateRoutineQueryPageTest {

	private static final String TEST_NAME_VALUE = "TestName";
	private static final String TEST_JQL_VALUE = "TestJQL";
	private static final String TEST_MINUTES_VALUE = "12";
	private static final long TEST_USER_ID = 4711;

	@Mock
	private RoutineQueryService routineQueryServiceMock;

	private WicketTester wicketTester;

	@Before
	public void setUp() throws Exception {
		wicketTester = new WicketTester(new TestApplication(this));
		TestSession testSession = TestSession.get();
		testSession.setRoles(new Roles("administrator"));
		testSession.setUserId(TEST_USER_ID);
		wicketTester.startPage(new CreateRoutineQueryPage());
	}

	@Test
	public void rendersSuccessfull() throws Exception {
		wicketTester.assertComponent("createRoutineForm", Form.class);
		wicketTester.assertComponent("createRoutineForm:routineQueryPanel", RoutineQueryPanel.class);
		wicketTester.assertComponent("createRoutineForm:submitButton", AjaxFallbackButton.class);
	}

	@Test(expected = RestartResponseAtInterceptPageException.class)
	public void redirectsToLoginPageWhenRoleIsNotCorrect() throws Exception {
		TestSession.get().setRoles(new Roles("noRole"));
		wicketTester.startPage(new CreateRoutineQueryPage());
	}

	@Test
	public void validFormDataCallsServiceToPersistRoutineQuery() throws Exception {

		when(routineQueryServiceMock.checkJql(TEST_JQL_VALUE)).thenReturn(true);

		FormTester formTester = wicketTester.newFormTester("createRoutineForm");
		formTester.setValue("routineQueryPanel:routineForm:routineNamePanel:input", TEST_NAME_VALUE);
		formTester.setValue("routineQueryPanel:routineForm:jqlForm:routineJqlPanel:input", TEST_JQL_VALUE);
		formTester.setValue("routineQueryPanel:routineForm:routineMinutesPanel:input", TEST_MINUTES_VALUE);

		formTester.submit("submitButton");

		ArgumentCaptor<RoutineQueryDTO> routineQueryDTOArgumentCaptor = ArgumentCaptor.forClass(RoutineQueryDTO.class);
		verify(routineQueryServiceMock).saveRoutineQuery(routineQueryDTOArgumentCaptor.capture(), eq(TEST_USER_ID));
		RoutineQueryDTO routineQueryDTO = routineQueryDTOArgumentCaptor.getValue();
		assertThat(routineQueryDTO.getMinutesForRecognition()).isEqualTo(Integer.parseInt(TEST_MINUTES_VALUE));
		assertThat(routineQueryDTO.getJqlString()).isEqualTo(TEST_JQL_VALUE);
		assertThat(routineQueryDTO.getName()).isEqualTo(TEST_NAME_VALUE);
		assertThat(routineQueryDTO.getRoutineQueryState()).isEqualTo(ACTIVE);
	}

	@Test
	public void formSubmitCallsRoutineQueryServiceForValidateJql() throws Exception {
		FormTester formTester = wicketTester.newFormTester("createRoutineForm");
		formTester.setValue("routineQueryPanel:routineForm:routineNamePanel:input", TEST_NAME_VALUE);
		formTester.setValue("routineQueryPanel:routineForm:jqlForm:routineJqlPanel:input", TEST_JQL_VALUE);
		formTester.setValue("routineQueryPanel:routineForm:routineMinutesPanel:input", TEST_MINUTES_VALUE);

		formTester.submit("submitButton");

		verify(routineQueryServiceMock).checkJql(TEST_JQL_VALUE);
	}

	@Test
	public void invalidFormDataShowsErrorMessage() throws Exception {
		FormTester formTester = wicketTester.newFormTester("createRoutineForm");
		formTester.setValue("routineQueryPanel:routineForm:routineNamePanel:input", TEST_NAME_VALUE);
		formTester.setValue("routineQueryPanel:routineForm:jqlForm:routineJqlPanel:input", TEST_JQL_VALUE);
		formTester.setValue("routineQueryPanel:routineForm:routineMinutesPanel:input", TEST_MINUTES_VALUE);

		formTester.submit("submitButton");

		wicketTester.assertErrorMessages("save.error.text");
	}
}
