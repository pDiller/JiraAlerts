package io.reflectoring.jiraalerts.integration.admin.firstconfiguration;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.Model;

import io.reflectoring.jiraalerts.integration.base.BasePage;
import io.reflectoring.jiraalerts.integration.homepage.HomePage;
import io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration.FirstConfigurationPanel;
import io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration.JiraConnectionDataDTO;

/**
 * Page for the first configuration for JiraAlerts.
 */
public class FirstConfigurationPage extends BasePage {

	public FirstConfigurationPage() {
		Model<JiraConnectionDataDTO> jiraConnectionDataDTOModel = new Model<>(new JiraConnectionDataDTO());
		add(new FirstConfigurationPanel("firstConfigurationPanel", jiraConnectionDataDTOModel) {

			@Override
			protected void onJiraConnectionSaved(AjaxRequestTarget target) {
				setResponsePage(HomePage.class);
			}
		});
	}
}
