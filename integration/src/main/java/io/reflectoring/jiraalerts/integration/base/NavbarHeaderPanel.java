package io.reflectoring.jiraalerts.integration.base;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;

/**
 * Panel for the Header Including the Navigation.
 */
class NavbarHeaderPanel extends Panel {

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
		navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT));
		add(navbar);
	}
}
