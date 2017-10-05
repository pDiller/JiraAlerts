package io.reflectoring.jiraalerts.application;

import org.apache.wicket.Application;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

import io.reflectoring.jiraalerts.integration.admin.applicationactivation.ActivateApplicationPage;
import io.reflectoring.jiraalerts.integration.admin.firstconfiguration.FirstConfigurationPage;

public class InitialActivationListener implements IRequestCycleListener {

	private boolean initialized = false;

	@Override
	public void onBeginRequest(RequestCycle cycle) {
		JiraAlertsApplication jiraAlertsApplication = (JiraAlertsApplication) Application.get();
		if (!initialized && jiraAlertsApplication.isFirstConfiguration()) {
			cycle.setResponsePage(FirstConfigurationPage.class);
			initialized = true;
		} else if (!initialized && !jiraAlertsApplication.isApplicationActivated()) {
			cycle.setResponsePage(ActivateApplicationPage.class);
			initialized = true;
		}
	}
}
