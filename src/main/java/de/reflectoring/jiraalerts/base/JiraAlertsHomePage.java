package de.reflectoring.jiraalerts.base;

import de.reflectoring.jiraalerts.jiracomponent.configuration.JiraUrlConfigurationPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.ResourceModel;

public class JiraAlertsHomePage extends WebPage{

    public JiraAlertsHomePage(){
        add(new Label("welcomeLabel", new ResourceModel("welcome.text")));
        add(new JiraUrlConfigurationPanel("jiraUrlConfigurationPanel"));
    }
}
