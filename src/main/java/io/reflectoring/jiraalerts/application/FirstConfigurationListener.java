package io.reflectoring.jiraalerts.application;

import io.reflectoring.jiraalerts.integration.admin.firstconfiguration.FirstConfigurationPage;
import org.apache.wicket.Application;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

public class FirstConfigurationListener implements IRequestCycleListener {

    private boolean initialized = false;

    @Override
    public void onBeginRequest(RequestCycle cycle) {
        JiraAlertsApplication jiraAlertsApplication = (JiraAlertsApplication) Application.get();
        if (!initialized && jiraAlertsApplication.isFirstConfiguration()) {
            cycle.setResponsePage(FirstConfigurationPage.class);
            initialized = true;
        }
    }
}
