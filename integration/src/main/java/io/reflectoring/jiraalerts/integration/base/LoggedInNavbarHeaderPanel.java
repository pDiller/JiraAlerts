package io.reflectoring.jiraalerts.integration.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;

import io.reflectoring.jiraalerts.integration.homepage.HomePage;

/**
 * Panel for the Header Including the Navigation.
 */
class LoggedInNavbarHeaderPanel extends Panel {

	/**
	 * Constructor for NavBarHeaderPanel.
	 *
	 * @param id
	 *            the Wicket-Id.
	 */
	LoggedInNavbarHeaderPanel(String id) {
		super(id);

		Navbar navbar = new Navbar("headerNavbar");
		navbar.fluid();
		navbar.setBrandName(new ResourceModel("navbarHeader.brandname"));

		List<Component> navbarButtons = new ArrayList<>();
		NavbarButton homeButton = new NavbarButton(HomePage.class, new ResourceModel("navbarHeader.home"));
		navbarButtons.add(homeButton);
		navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT, navbarButtons.toArray(new Component[navbarButtons.size()])));
		add(navbar);
	}
}
