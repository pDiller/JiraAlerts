package io.reflectoring.jiraalerts.dashboard.routine;

import java.io.Serializable;

import io.reflectoring.jiraalerts.routine.RoutineQuery;
import io.reflectoring.jiraalerts.routine.RoutineQueryState;

/**
 * Transferobject for {@link RoutineQuery}.
 */
public class RoutineQueryDTO implements Serializable {

	private Long id;

	private String name;

	private String jqlString;

	private int minutesForRecognition;

	private RoutineQueryState routineQueryState;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJqlString() {
		return jqlString;
	}

	public void setJqlString(String jqlString) {
		this.jqlString = jqlString;
	}

	public int getMinutesForRecognition() {
		return minutesForRecognition;
	}

	public void setMinutesForRecognition(int minutesForRecognition) {
		this.minutesForRecognition = minutesForRecognition;
	}

	public RoutineQueryState getRoutineQueryState() {
		return routineQueryState;
	}

	public void setRoutineQueryState(RoutineQueryState routineQueryState) {
		this.routineQueryState = routineQueryState;
	}
}
