package io.reflectoring.jiraalerts.integration.base;

import org.apache.wicket.markup.html.WebPage;

public class BasePage extends WebPage{

    public BasePage() {
        add(new NavbarHeaderPanel("navbarHeaderPanel"));
    }
}
