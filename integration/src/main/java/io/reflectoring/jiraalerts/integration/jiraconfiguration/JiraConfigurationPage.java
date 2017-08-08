package io.reflectoring.jiraalerts.integration.jiraconfiguration;

import io.reflectoring.jiraalerts.integration.base.BasePage;
import io.reflectoring.jiraalerts.jiracomponent.configuration.JiraConnectionDataPanel;

/** Page for Jira-Configuration. */
public class JiraConfigurationPage extends BasePage {

	/** Constructor for JiraConfigurationPage. */
	public JiraConfigurationPage() {
		add(new JiraConnectionDataPanel("jiraUrlConfigurationPanel"));
	}
}
