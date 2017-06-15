package io.reflectoring.jiraalerts.integration.base;

import org.apache.wicket.markup.html.WebPage;

/** The Page with Basic funktionaltiy, like ne Navigation. */
public class BasePage extends WebPage {

	/** Constructor for BasePage. */
	public BasePage() {
		add(new NavbarHeaderPanel("navbarHeaderPanel"));
	}
}
