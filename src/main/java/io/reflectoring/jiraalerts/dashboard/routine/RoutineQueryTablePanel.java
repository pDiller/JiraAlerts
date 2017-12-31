package io.reflectoring.jiraalerts.dashboard.routine;

import org.apache.wicket.markup.html.panel.Panel;

public class RoutineQueryTablePanel extends Panel {

	public RoutineQueryTablePanel(String id, long userId) {
		super(id);

		add(new RoutineQueryTable("routineQueryTable", userId));
	}
}
