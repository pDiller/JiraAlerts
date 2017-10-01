package io.reflectoring.jiraalerts.application;

import io.reflectoring.jiraalerts.integration.admin.FirstConfigurationService;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;

import io.reflectoring.jiraalerts.integration.admin.AdministrationPage;
import io.reflectoring.jiraalerts.integration.homepage.HomePage;
import io.reflectoring.jiraalerts.integration.jiraconfiguration.JiraConfigurationPage;

@Component
public class JiraAlertsApplication extends WebApplication {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private FirstConfigurationService firstConfigurationService;

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

	@Override
	protected void init() {
		super.init();
		getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext));
//		mountPages();

		BootstrapSettings bootstrapSettings = new BootstrapSettings();
		Bootstrap.install(this, bootstrapSettings);

		getRequestCycleListeners().add(new FirstConfigurationListener());
	}

//	private void mountPages() {
//		mountPage("/jira-configuration", JiraConfigurationPage.class);
//		mountPage("/administration", AdministrationPage.class);
//	}

	public boolean isFirstConfiguration(){
		return firstConfigurationService.isFirstConfiguration();
	}
}
