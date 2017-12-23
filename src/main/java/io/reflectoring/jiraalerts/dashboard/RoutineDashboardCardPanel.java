package io.reflectoring.jiraalerts.dashboard;

import org.apache.wicket.model.IModel;

/**
 * Dashboard-Card for routines.
 */
public class RoutineDashboardCardPanel extends AbstractDashboardCardPanel<String> {

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-ID.
	 * @param model
	 *            Wicket-Model.
	 */
	public RoutineDashboardCardPanel(String id, IModel<String> model) {
		super(id, model);
	}
}
