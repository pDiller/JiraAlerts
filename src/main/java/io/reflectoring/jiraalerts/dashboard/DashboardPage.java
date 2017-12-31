package io.reflectoring.jiraalerts.dashboard;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import io.reflectoring.jiraalerts.application.state.ApplicationState;
import io.reflectoring.jiraalerts.application.state.ApplicationStateModel;
import io.reflectoring.jiraalerts.base.BasePage;
import io.reflectoring.jiraalerts.dashboard.applicationstate.InitializeJiraLoginDTOModel;
import io.reflectoring.jiraalerts.dashboard.applicationstate.JiraLoginDTO;
import io.reflectoring.jiraalerts.dashboard.routine.RoutineQueryDTO;

/**
 * Dashboard for JiraAlerts functions.
 */
@AuthorizeInstantiation("administrator")
public class DashboardPage extends BasePage {

	/**
	 * Constructor
	 */
	public DashboardPage() {

		add(new RoutineDashboardCardPanel("routinePanel", new Model<>(new RoutineQueryDTO())));

		add(new ProfileDashboardCardPanel("profilePanel", new Model<>()));

		add(new UserManagementDashboardCardPanel("userManagementPanel", new Model<>()));

		IModel<JiraLoginDTO> initialJiraLoginDTOModel = new InitializeJiraLoginDTOModel();
		IModel<ApplicationState> applicationStateModel = new ApplicationStateModel();
		add(new ApplicationStateDashboardCardPanel("applicationStatePanel", initialJiraLoginDTOModel, applicationStateModel));
	}
}
