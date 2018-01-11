package io.reflectoring.jiraalerts.dashboard;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

import io.reflectoring.jiraalerts.application.JiraAlertsSession;
import io.reflectoring.jiraalerts.dashboard.routine.RoutineQueriesDetailPage;
import io.reflectoring.jiraalerts.dashboard.routine.RoutineQueryOverviewTable;

/**
 * Dashboard-Card for routines.
 */
public class RoutineDashboardCardPanel extends AbstractDashboardCardPanel<Void> {

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-ID.
	 */
	public RoutineDashboardCardPanel(String id) {
		super(id);

		add(new RoutineQueryOverviewTable("routineQueryTable", JiraAlertsSession.get().getUserId()));

		add(new BookmarkablePageLink<>("showAllRoutinesLink", RoutineQueriesDetailPage.class));
	}
}
