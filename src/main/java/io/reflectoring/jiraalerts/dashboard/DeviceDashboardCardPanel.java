package io.reflectoring.jiraalerts.dashboard;

import org.apache.wicket.model.IModel;

/**
 * Dashboard-Card for devices.
 */
public class DeviceDashboardCardPanel extends AbstractDashboardCardPanel<String> {

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-ID.
	 * @param model
	 *            Wicket-Model.
	 */
	public DeviceDashboardCardPanel(String id, IModel<String> model) {
		super(id, model);
	}
}
