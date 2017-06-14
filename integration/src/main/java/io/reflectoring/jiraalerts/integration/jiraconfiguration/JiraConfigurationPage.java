package io.reflectoring.jiraalerts.integration.jiraconfiguration;

import io.reflectoring.jiraalerts.integration.base.BasePage;
import io.reflectoring.jiraalerts.jiracomponent.configuration.JiraUrlConfigurationPanel;

public class JiraConfigurationPage extends BasePage {

    public JiraConfigurationPage() {
        add(new JiraUrlConfigurationPanel("jiraUrlConfigurationPanel"));
    }
}
