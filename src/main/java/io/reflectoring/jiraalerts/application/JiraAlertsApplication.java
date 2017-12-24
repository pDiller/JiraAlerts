package io.reflectoring.jiraalerts.application;

import javax.inject.Inject;

import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import io.reflectoring.jiraalerts.applicationstate.SetupApplicationService;
import io.reflectoring.jiraalerts.dashboard.DashboardPage;
import io.reflectoring.jiraalerts.login.LoginPage;

/**
 * Wickets application. Initializes and configures the configuration.
 */
@Component
public class JiraAlertsApplication extends AuthenticatedWebApplication {

	@Inject
	private ApplicationContext applicationContext;

	@Inject
	private SetupApplicationService setupApplicationService;

	@Override
	public Class<? extends Page> getHomePage() {
		return LoginPage.class;
	}

	@Override
	protected void init() {
		super.init();

		getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext));

		initializeApplicationState();

		mountPage("login.html", LoginPage.class);
		mountPage("dashboard.html", DashboardPage.class);
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return JiraAlertsSession.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LoginPage.class;
	}

	private void initializeApplicationState() {
		setupApplicationService.initializeApplicationState();
	}
}
