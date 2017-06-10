package de.reflectoring.jiraalerts.application;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import de.reflectoring.jiraalerts.base.JiraAlertsHomePage;

public class JiraAlertsApplication extends WebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
        return JiraAlertsHomePage.class;
    }
}
