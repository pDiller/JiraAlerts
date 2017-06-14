package io.reflectoring.jiraalerts.integration.homepage;

import io.reflectoring.jiraalerts.integration.base.BasePage;
import io.reflectoring.jiraalerts.jiracomponent.configuration.JiraUrlConfigurationPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.ResourceModel;

/**
 * Welcomepage for JiraAlerts.
 */
public class HomePage extends BasePage{

    /**
     * Constructor for HomePage.
     */
    public HomePage(){
        add(new Label("welcomeLabel", new ResourceModel("welcome.text")));
    }
}
