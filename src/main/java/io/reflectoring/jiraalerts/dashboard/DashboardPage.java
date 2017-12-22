package io.reflectoring.jiraalerts.dashboard;

import io.reflectoring.jiraalerts.base.BasePage;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.model.Model;

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

        add(new ApplicationStatusDashboardCardPanel("applicationStatusPanel", new Model<>()));
    }
}
