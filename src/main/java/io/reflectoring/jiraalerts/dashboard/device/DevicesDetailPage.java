package io.reflectoring.jiraalerts.dashboard.device;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

import io.reflectoring.jiraalerts.application.JiraAlertsSession;
import io.reflectoring.jiraalerts.base.BasePage;
import io.reflectoring.jiraalerts.dashboard.DashboardPage;

/**
 * Detail page listing all devices.
 */
@AuthorizeInstantiation("administrator")
public class DevicesDetailPage extends BasePage {

	public DevicesDetailPage() {
		add(new DeviceDetailsTable("deviceTable", JiraAlertsSession.get().getUserId()));

		add(new BookmarkablePageLink<>("backToDashboardLink", DashboardPage.class));

		add(new BookmarkablePageLink<>("createDeviceLink", CreateDevicePage.class));
	}
}
