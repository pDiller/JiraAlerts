package io.reflectoring.jiraalerts.dashboard;

import static io.reflectoring.jiraalerts.appstate.ApplicationState.*;
import static org.mockito.Mockito.mock;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.application.testsetup.TestApplication;
import io.reflectoring.jiraalerts.appstate.ApplicationState;
import io.reflectoring.jiraalerts.common.AjaxEventPayload;
import io.reflectoring.jiraalerts.dashboard.applicationstate.*;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationStateDashboardCardPanelTest {

	private static final String TEST_URL = "url";
	private static final String TEST_USERNAME = "username";
	private static final String TEST_PASSWORD = "password";

	private WicketTester wicketTester;
	private JiraLoginDTO jiraLoginDTO;

	@Before
	public void setUp() throws Exception {
		wicketTester = new WicketTester(new TestApplication(this));
		jiraLoginDTO = new JiraLoginDTO();
		jiraLoginDTO.setUrl(TEST_URL);
		jiraLoginDTO.setUsername(TEST_USERNAME);
		jiraLoginDTO.setPassword(TEST_PASSWORD);
	}

	@Test
	public void activeComponentRenders() throws Exception {
		wicketTester.startComponentInPage(new ApplicationStateDashboardCardPanel("panel", new Model<>(jiraLoginDTO), Model.of(ACTIVE)));
		wicketTester.assertComponent("panel:stateComponent", LoggedInApplicationPanel.class);
	}

	@Test
	public void notInitializedComponentRenders() throws Exception {
		wicketTester.startComponentInPage(new ApplicationStateDashboardCardPanel("panel", new Model<>(jiraLoginDTO), Model.of(NOT_INITIALIZED)));
		wicketTester.assertComponent("panel:stateComponent", SetupApplicationPanel.class);
	}

	@Test
	public void notActiveComponentRenders() throws Exception {
		wicketTester.startComponentInPage(new ApplicationStateDashboardCardPanel("panel", new Model<>(jiraLoginDTO), Model.of(NOT_ACTIVE)));
		wicketTester.assertComponent("panel:stateComponent", ConnectApplicationPanel.class);
	}

	@Test
	public void fromNotInitializedToActive() throws Exception {
		Model<ApplicationState> applicationStateModel = Model.of(NOT_INITIALIZED);
		ApplicationStateDashboardCardPanel panel = new ApplicationStateDashboardCardPanel("panel", new Model<>(jiraLoginDTO), applicationStateModel);
		wicketTester.startComponentInPage(panel);

		wicketTester.assertComponent("panel:stateComponent", SetupApplicationPanel.class);

		applicationStateModel.setObject(ACTIVE);

		panel.send(panel, Broadcast.BREADTH, new RerenderApplicationStateCardEventPayload(mock(AjaxRequestTarget.class)));

		wicketTester.assertComponent("panel:stateComponent", LoggedInApplicationPanel.class);
	}

	@Test
	public void otherEventsMakesNoDifference() throws Exception {
		ApplicationStateDashboardCardPanel panel = new ApplicationStateDashboardCardPanel("panel", new Model<>(jiraLoginDTO),
		        Model.of(NOT_INITIALIZED));
		wicketTester.startComponentInPage(panel);
		wicketTester.assertComponent("panel:stateComponent", SetupApplicationPanel.class);

		panel.send(panel, Broadcast.BREADTH, new AjaxEventPayload(mock(AjaxRequestTarget.class)) {});

		wicketTester.assertComponent("panel:stateComponent", SetupApplicationPanel.class);
	}
}
