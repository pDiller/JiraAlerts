package io.reflectoring.jiraalerts.dashboard;

import org.apache.wicket.model.IModel;

/**
 * Dashboard-Card for profile.
 */
public class ProfileDashboardCardPanel extends AbstractDashboardCardPanel<String> {

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-ID.
	 * @param model
	 *            Wicket-Model.
	 */
	public ProfileDashboardCardPanel(String id, IModel<String> model) {
		super(id, model);
	}
}
