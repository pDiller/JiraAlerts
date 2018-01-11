package io.reflectoring.jiraalerts.dashboard;

import org.apache.wicket.model.IModel;

/**
 * Dashboard-Card for devices.
 */
public class DevicesDashboardCardPanel extends AbstractDashboardCardPanel<String> {

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-ID.
	 * @param model
	 *            Wicket-Model.
	 */
	public DevicesDashboardCardPanel(String id, IModel<String> model) {
		super(id, model);
	}
}
