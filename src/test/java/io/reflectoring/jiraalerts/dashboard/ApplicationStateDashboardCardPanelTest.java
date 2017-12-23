package io.reflectoring.jiraalerts.dashboard;

import static io.reflectoring.jiraalerts.application.ApplicationState.ACTIVE;
import static io.reflectoring.jiraalerts.application.ApplicationState.NOT_INITIALIZED;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.application.ApplicationStateService;
import io.reflectoring.jiraalerts.application.TestApplication;
import io.reflectoring.jiraalerts.applicationstate.JiraLoginDTO;
import io.reflectoring.jiraalerts.applicationstate.LoggedInApplicationPanel;
import io.reflectoring.jiraalerts.applicationstate.SetupApplicationPanel;
import io.reflectoring.jiraalerts.common.AjaxEventPayload;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationStateDashboardCardPanelTest {

	private static final String TEST_URL = "url";
	private static final String TEST_USERNAME = "username";
	private static final String TEST_PASSWORD = "password";

	@Mock
	private ApplicationStateService applicationStateServiceMock;

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
		when(applicationStateServiceMock.getApplicationState()).thenReturn(ACTIVE);
		wicketTester.startComponentInPage(new ApplicationStateDashboardCardPanel("panel", new Model<>(jiraLoginDTO)));
		wicketTester.assertComponent("panel:setupApplicationPanel", LoggedInApplicationPanel.class);
	}

	@Test
	public void notInitializedComponentRenders() throws Exception {
		when(applicationStateServiceMock.getApplicationState()).thenReturn(NOT_INITIALIZED);
		wicketTester.startComponentInPage(new ApplicationStateDashboardCardPanel("panel", new Model<>(jiraLoginDTO)));
		wicketTester.assertComponent("panel:setupApplicationPanel", SetupApplicationPanel.class);
	}

	@Test
	public void fromNotInitializedToActive() throws Exception {
		when(applicationStateServiceMock.getApplicationState()).thenReturn(NOT_INITIALIZED, ACTIVE);
		ApplicationStateDashboardCardPanel panel = new ApplicationStateDashboardCardPanel("panel", new Model<>(jiraLoginDTO));
		wicketTester.startComponentInPage(panel);
		wicketTester.assertComponent("panel:setupApplicationPanel", SetupApplicationPanel.class);

		panel.send(panel, Broadcast.BREADTH, new RerenderApplicationStateCardEventPayload(mock(AjaxRequestTarget.class)));

		wicketTester.assertComponent("panel:setupApplicationPanel", LoggedInApplicationPanel.class);
	}

	@Test
	public void otherEventsMakesNoDifference() throws Exception {
		when(applicationStateServiceMock.getApplicationState()).thenReturn(NOT_INITIALIZED, ACTIVE);
		ApplicationStateDashboardCardPanel panel = new ApplicationStateDashboardCardPanel("panel", new Model<>(jiraLoginDTO));
		wicketTester.startComponentInPage(panel);
		wicketTester.assertComponent("panel:setupApplicationPanel", SetupApplicationPanel.class);

		panel.send(panel, Broadcast.BREADTH, new AjaxEventPayload(mock(AjaxRequestTarget.class)) {});

		wicketTester.assertComponent("panel:setupApplicationPanel", SetupApplicationPanel.class);
	}
}
