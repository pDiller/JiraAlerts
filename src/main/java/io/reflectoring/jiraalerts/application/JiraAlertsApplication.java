package io.reflectoring.jiraalerts.application;

import io.reflectoring.jiraalerts.base.JiraAlertsHomePage;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

public class JiraAlertsApplication extends WebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
        return JiraAlertsHomePage.class;
    }

    @Override
    protected void init() {
        super.init();
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
    }
}