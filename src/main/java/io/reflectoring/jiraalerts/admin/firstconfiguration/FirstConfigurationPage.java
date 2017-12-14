package io.reflectoring.jiraalerts.admin.firstconfiguration;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import io.reflectoring.jiraalerts.admin.applicationactivation.ActivateApplicationPage;
import io.reflectoring.jiraalerts.base.BasePage;

/**
 * Page for the first configuration for JiraAlerts.
 */
public class FirstConfigurationPage extends BasePage {

	public FirstConfigurationPage() {
		IModel<JiraConnectionDataDTO> jiraConnectionDataDTOModel = new Model<>(new JiraConnectionDataDTO());
		add(new FirstConfigurationPanel("firstConfigurationPanel", jiraConnectionDataDTOModel) {

			@Override
			protected void onJiraConnectionSaved(AjaxRequestTarget target) {
				setResponsePage(ActivateApplicationPage.class);
			}
		});
	}
}
