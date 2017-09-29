package io.reflectoring.jiraalerts.integration.admin;

import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.spring.injection.annot.SpringBean;

import io.reflectoring.jiraalerts.integration.admin.persistence.FirstConfiguration;
import io.reflectoring.jiraalerts.integration.base.BasePage;
import io.reflectoring.jiraalerts.jiracomponent.configuration.JiraConnectionDataPanel;

public class AdministrationPage extends BasePage {

	@SpringBean
	private FirstConfigurationService firstConfigurationService;

	@SpringBean
	private ApplicationStatusService applicationStatusService;

	private JiraConnectionDataPanel jiraConnectionDataPanel;

	public AdministrationPage() {

		jiraConnectionDataPanel = new JiraConnectionDataPanel("JiraConnectionDataPanel") {

			@Override
			protected void submitConnectionData(AjaxRequestTarget ajaxRequestTarget) {
				applicationStatusService.activateApplication();
				FirstConfiguration firstConfiguration = new FirstConfiguration();
				firstConfiguration.setFirstConfiguration(true);
				firstConfiguration.setConfiguredAt(new Date());
				firstConfigurationService.storeFirstConfiguration(firstConfiguration);
			}
		};
		add(jiraConnectionDataPanel);
	}

	@Override
	protected void onConfigure() {
		jiraConnectionDataPanel.setVisible(firstConfigurationService.isFirstConfiguration());
	}
}
