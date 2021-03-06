package io.reflectoring.jiraalerts.dashboard;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

import io.reflectoring.jiraalerts.application.JiraAlertsSession;
import io.reflectoring.jiraalerts.dashboard.device.DeviceOverviewTable;
import io.reflectoring.jiraalerts.dashboard.device.DevicesDetailPage;

/**
 * Dashboard-Card for devices.
 */
public class DeviceDashboardCardPanel extends AbstractDashboardCardPanel<Void> {

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-ID.
	 */
	public DeviceDashboardCardPanel(String id) {
		super(id);

		add(new DeviceOverviewTable("deviceTable", JiraAlertsSession.get().getUserId()));

		add(new BookmarkablePageLink<>("showAllDevicesLink", DevicesDetailPage.class));
	}
}
