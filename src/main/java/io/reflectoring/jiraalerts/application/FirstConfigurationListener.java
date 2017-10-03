package io.reflectoring.jiraalerts.application;

import org.apache.wicket.Application;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

import io.reflectoring.jiraalerts.integration.admin.firstconfiguration.FirstConfigurationPage;

/**
 * Listener for first configuration of JiraAlerts. Redirects to {@link FirstConfigurationPage} at JiraAlerts first start.
 */
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
