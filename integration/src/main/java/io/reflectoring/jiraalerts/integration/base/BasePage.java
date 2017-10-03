package io.reflectoring.jiraalerts.integration.base;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.agilecoders.wicket.core.markup.html.bootstrap.heading.Heading;

import io.reflectoring.jiraalerts.jiracomponent.admin.applicationactivation.ActivateApplicationService;

/**
 * The Page with Basic funktionaltiy, like ne Navigation.
 */
public abstract class BasePage extends WebPage {

	@SpringBean
	private ActivateApplicationService activateApplicationService;

	/** Constructor for LoggedInBasePage. */
	public BasePage() {
		add(new NavbarHeaderPanel("navbarHeaderPanel"));

		Heading pageHeading = new Heading("pageHeading", new StringResourceModel("pageHeading", this));
		add(pageHeading);
	}
}
