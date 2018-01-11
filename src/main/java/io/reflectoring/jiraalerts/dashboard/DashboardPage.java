package io.reflectoring.jiraalerts.dashboard;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import io.reflectoring.jiraalerts.application.state.ApplicationState;
import io.reflectoring.jiraalerts.application.state.ApplicationStateModel;
import io.reflectoring.jiraalerts.base.BasePage;
import io.reflectoring.jiraalerts.dashboard.applicationstate.InitializeJiraLoginDTOModel;
import io.reflectoring.jiraalerts.dashboard.applicationstate.JiraLoginDTO;

/**
 * Dashboard for JiraAlerts functions.
 */
@AuthorizeInstantiation("administrator")
public class DashboardPage extends BasePage {

	/**
	 * Constructor
	 */
	public DashboardPage() {

		add(new RoutineDashboardCardPanel("routinePanel", new Model<>()));

		add(new DeviceDashboardCardPanel("devicePanel"));

		add(new UserManagementDashboardCardPanel("userManagementPanel", new Model<>()));

		IModel<JiraLoginDTO> initialJiraLoginDTOModel = new InitializeJiraLoginDTOModel();
		IModel<ApplicationState> applicationStateModel = new ApplicationStateModel();
		add(new ApplicationStateDashboardCardPanel("applicationStatePanel", initialJiraLoginDTOModel, applicationStateModel));
	}
}
