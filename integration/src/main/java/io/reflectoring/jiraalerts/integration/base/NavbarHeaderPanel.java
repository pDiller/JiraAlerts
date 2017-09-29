package io.reflectoring.jiraalerts.integration.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;

import io.reflectoring.jiraalerts.integration.admin.ApplicationStatusService;
import io.reflectoring.jiraalerts.integration.homepage.HomePage;
import io.reflectoring.jiraalerts.integration.jiraconfiguration.JiraConfigurationPage;

/**
 * Panel for the Header Including the Navigation.
 */
class NavbarHeaderPanel extends Panel {

	@SpringBean
	private ApplicationStatusService applicationStatusService;

	/**
	 * Constructor for NavBarHeaderPanel.
	 *
	 * @param id
	 *            the Wicket-Id.
	 */
	NavbarHeaderPanel(String id) {
		super(id);

		Navbar navbar = new Navbar("headerNavbar");
		navbar.fluid();
		navbar.setBrandName(new ResourceModel("navbarHeader.brandname"));

		List<Component> navbarButtons = new ArrayList<>();

		NavbarButton homeButton = new NavbarButton(HomePage.class, new ResourceModel("navbarHeader.home"));
		navbarButtons.add(homeButton);

		if (applicationStatusService.isApplicationActivated()) {
			NavbarButton jiraConfigurationButton = new NavbarButton(JiraConfigurationPage.class,
			        new ResourceModel("navbarHeader.jira.configuration"));
			navbarButtons.add(jiraConfigurationButton);
		}

		navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT, navbarButtons.toArray(new Component[navbarButtons.size()])));
		add(navbar);
	}
}
