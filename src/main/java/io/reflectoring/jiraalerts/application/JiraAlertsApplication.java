package io.reflectoring.jiraalerts.application;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;

import io.reflectoring.jiraalerts.integration.Login.LoginPage;
import io.reflectoring.jiraalerts.jiracomponent.admin.applicationactivation.ActivateApplicationService;
import io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration.JiraConnectionDataService;

@Component
public class JiraAlertsApplication extends WebApplication {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private JiraConnectionDataService jiraConnectionDataService;

	@Autowired
	private ActivateApplicationService activateApplicationService;

	@Override
	public Class<? extends Page> getHomePage() {
		return LoginPage.class;
	}

	@Override
	protected void init() {
		super.init();
		getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext));

		BootstrapSettings bootstrapSettings = new BootstrapSettings();
		Bootstrap.install(this, bootstrapSettings);

		getRequestCycleListeners().add(new InitialActivationListener());
	}

	boolean isFirstConfiguration() {
		return jiraConnectionDataService.isFirstConfiguration();
	}

	public boolean isApplicationActivated() {
		return activateApplicationService.isApplicationActivated();
	}
}
