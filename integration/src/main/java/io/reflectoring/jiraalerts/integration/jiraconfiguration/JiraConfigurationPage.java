package io.reflectoring.jiraalerts.integration.jiraconfiguration;

import io.reflectoring.jiraalerts.integration.base.BasePage;
import io.reflectoring.jiraalerts.jiracomponent.configuration.JiraUrlConfigurationPanel;

/** Page for Jira-Configuration. */
public class JiraConfigurationPage extends BasePage {

	/** Constructor for JiraConfigurationPage. */
	public JiraConfigurationPage() {
		add(new JiraUrlConfigurationPanel("jiraUrlConfigurationPanel"));
	}
}
