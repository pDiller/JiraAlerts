package io.reflectoring.jiraalerts.application;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import io.reflectoring.jiraalerts.integration.homepage.HomePage;
import io.reflectoring.jiraalerts.integration.jiraconfiguration.JiraConfigurationPage;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class JiraAlertsApplication extends WebApplication {

  @Autowired private ApplicationContext applicationContext;

  @Override
  public Class<? extends Page> getHomePage() {
    return HomePage.class;
  }

  @Override
  protected void init() {
    super.init();
    getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext));
    mountPages();

    BootstrapSettings bootstrapSettings = new BootstrapSettings();
    Bootstrap.install(this, bootstrapSettings);
  }

  private void mountPages() {
    mountPage("/jira-configuration", JiraConfigurationPage.class);
  }
}
