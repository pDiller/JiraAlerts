package de.reflectoring.jiraalerts.application;

import de.reflectoring.jiraalerts.base.JiraAlertsHomePage;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class JiraAlertsApplication extends WebApplication{

    @Override
    public Class<? extends Page> getHomePage() {
        return JiraAlertsHomePage.class;
    }
}
