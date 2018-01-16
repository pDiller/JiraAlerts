package io.reflectoring.jiraalerts.dashboard.routine;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import io.reflectoring.jiraalerts.application.login.User;

/**
 * Entity for the query-part of the Routines.
 */
@Entity
@Table(name = "ROUTINE_QUERY")
@TypeDefs(@TypeDef(typeClass = RoutineQueryStateType.class, name = "routineQueryStateType"))
public class RoutineQuery implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "JQL", nullable = false)
	private String jql;

	@Column(name = "MINUTES_FOR_RECOGNITION", nullable = false)
	private int minutesForRecognition;

	@Column(name = "ROUTINE_QUERY_STATE", nullable = false)
	@Type(type = "routineQueryStateType")
	private RoutineQueryState routineQueryState;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OWNER")
	private User owner;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJql() {
		return jql;
	}

	public void setJql(String jql) {
		this.jql = jql;
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}
