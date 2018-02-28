package io.reflectoring.jiraalerts.dashboard.routine;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

import io.reflectoring.jiraalerts.application.JiraAlertsSession;
import io.reflectoring.jiraalerts.base.BasePage;
import io.reflectoring.jiraalerts.dashboard.DashboardPage;

/**
 * Page to show the routines in detail.
 */
@AuthorizeInstantiation("administrator")
public class RoutineQueriesDetailPage extends BasePage {

	/**
	 * Constructor.
	 */
	public RoutineQueriesDetailPage() {
		add(new RoutineQueryDetailsTable("routineQueriesTable", JiraAlertsSession.get().getUserId()));

		add(new BookmarkablePageLink<>("backToDashboardLink", DashboardPage.class));
		add(new BookmarkablePageLink<>("createRoutineQueryLink", CreateRoutineQueryPage.class));
	}
}
