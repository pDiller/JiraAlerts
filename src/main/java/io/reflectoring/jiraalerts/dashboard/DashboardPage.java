package io.reflectoring.jiraalerts.dashboard;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import io.reflectoring.jiraalerts.applicationstate.InitializeJiraLoginDTOModel;
import io.reflectoring.jiraalerts.applicationstate.JiraLoginDTO;
import io.reflectoring.jiraalerts.base.BasePage;

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

		add(new ProfileDashboardCardPanel("profilePanel", new Model<>()));

		add(new UserManagementDashboardCardPanel("userManagementPanel", new Model<>()));

		IModel<JiraLoginDTO> initialJiraLoginDTOModel = new InitializeJiraLoginDTOModel();
		add(new ApplicationStateDashboardCardPanel("applicationStatePanel", initialJiraLoginDTOModel));
	}
}
