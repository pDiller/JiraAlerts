package io.reflectoring.jiraalerts.dashboard;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

import io.reflectoring.jiraalerts.dashboard.device.DeviceDetailPage;

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

		// TODO table

		// TODO correct page
		add(new BookmarkablePageLink<>("showAllDevicesLink", DeviceDetailPage.class));
	}
}
