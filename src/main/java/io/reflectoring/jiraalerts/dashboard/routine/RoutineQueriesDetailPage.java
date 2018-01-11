package io.reflectoring.jiraalerts.dashboard.routine;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

import io.reflectoring.jiraalerts.application.JiraAlertsSession;
import io.reflectoring.jiraalerts.base.BasePage;

@AuthorizeInstantiation("administrator")
public class RoutineQueriesDetailPage extends BasePage {

	public RoutineQueriesDetailPage() {

		add(new RoutineQueryDetailsTable("routineQueriesTable", JiraAlertsSession.get().getUserId()));
	}
}
