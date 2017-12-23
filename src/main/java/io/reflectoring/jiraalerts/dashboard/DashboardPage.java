package io.reflectoring.jiraalerts.dashboard;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.model.Model;

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

		// TODO The model has to load the given JIRA-connection-values if application is active.
		add(new ApplicationStateDashboardCardPanel("applicationStatePanel", new Model<>(new JiraLoginDTO())));
	}
}
