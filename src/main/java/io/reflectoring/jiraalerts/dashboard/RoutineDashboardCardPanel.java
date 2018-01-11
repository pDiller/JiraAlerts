package io.reflectoring.jiraalerts.dashboard;

import org.apache.wicket.model.IModel;

import io.reflectoring.jiraalerts.application.JiraAlertsSession;
import io.reflectoring.jiraalerts.dashboard.routine.RoutineQueryDTO;
import io.reflectoring.jiraalerts.dashboard.routine.RoutineQueryOverwievTablePanel;

/**
 * Dashboard-Card for routines.
 */
public class RoutineDashboardCardPanel extends AbstractDashboardCardPanel<RoutineQueryDTO> {

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-ID.
	 * @param model
	 *            Wicket-Model.
	 */
	public RoutineDashboardCardPanel(String id, IModel<RoutineQueryDTO> model) {
		super(id, model);

		add(new RoutineQueryOverwievTablePanel("routineQueryTablePanel", getModel(), JiraAlertsSession.get().getUserId()));
	}
}
