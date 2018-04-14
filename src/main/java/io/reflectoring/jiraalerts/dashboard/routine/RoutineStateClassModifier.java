package io.reflectoring.jiraalerts.dashboard.routine;

import java.util.Set;

import org.apache.wicket.ClassAttributeModifier;

import io.reflectoring.jiraalerts.routine.RoutineQueryState;

/*
 * Adds the CSS-class of the routine-state.
 */
class RoutineStateClassModifier extends ClassAttributeModifier {

	private RoutineQueryState routineQueryState;

	/**
	 * Constructor.
	 *
	 * @param routineQueryState
	 *            The routine-state.
	 */
	RoutineStateClassModifier(RoutineQueryState routineQueryState) {
		this.routineQueryState = routineQueryState;
	}

	@Override
	protected Set<String> update(Set<String> oldClasses) {
		oldClasses.add("badge");
		switch (routineQueryState) {
		case ACTIVE:
			oldClasses.add("badge-success");
			break;
		case NOT_ACTIVE:
			oldClasses.add("badge-warning");
			break;
		case ERROR:
			oldClasses.add("badge-danger");
			break;
		default:
			oldClasses.clear();
			break;
		}
		return oldClasses;
	}
}
