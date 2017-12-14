package io.reflectoring.jiraalerts.base;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

import io.reflectoring.jiraalerts.admin.applicationactivation.ActivateApplicationService;

/**
 * The Page with Basic funktionaltiy, like ne Navigation.
 */
public class LoggedInBasePage extends WebPage {

	@SpringBean
	private ActivateApplicationService activateApplicationService;

	/** Constructor for LoggedInBasePage. */
	public LoggedInBasePage() {
		add(new LoggedInNavbarHeaderPanel("loggedInNavbarHeaderPanel"));
	}
}
