package io.reflectoring.jiraalerts.integration.homepage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.ResourceModel;

import io.reflectoring.jiraalerts.integration.base.BasePage;

/**
 * Welcomepage for JiraAlerts.
 */
public class HomePage extends BasePage {

	/**
	 * Constructor for HomePage.
	 */
	public HomePage() {
		add(new Label("welcomeLabel", new ResourceModel("welcome.text")));
	}
}