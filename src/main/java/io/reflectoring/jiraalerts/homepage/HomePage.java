package io.reflectoring.jiraalerts.homepage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.ResourceModel;

import io.reflectoring.jiraalerts.base.LoggedInBasePage;

/**
 * Welcomepage for JiraAlerts.
 */
public class HomePage extends LoggedInBasePage {

	/**
	 * Constructor for HomePage.
	 */
	public HomePage() {
		add(new Label("welcomeLabel", new ResourceModel("welcome.text")));
	}

}
