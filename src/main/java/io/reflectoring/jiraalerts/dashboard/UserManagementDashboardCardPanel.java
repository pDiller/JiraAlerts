package io.reflectoring.jiraalerts.dashboard;

import org.apache.wicket.model.IModel;

/**
 * Dashboard-Card for usermanagement.
 */
public class UserManagementDashboardCardPanel extends AbstractDashboardCardPanel<String> {

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-ID.
	 * @param model
	 *            Wicket-Model.
	 */
	public UserManagementDashboardCardPanel(String id, IModel<String> model) {
		super(id, model);
	}
}
