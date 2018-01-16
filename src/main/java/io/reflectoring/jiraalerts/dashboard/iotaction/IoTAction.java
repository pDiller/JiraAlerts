package io.reflectoring.jiraalerts.dashboard.iotaction;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.*;

import io.reflectoring.jiraalerts.dashboard.device.Device;
import io.reflectoring.jiraalerts.dashboard.routine.RoutineQuery;

/**
 * Entity for IoT-Actions.
 */
@Entity
@Table(name = "IOT_ACTION", uniqueConstraints = { @UniqueConstraint(columnNames = { "DEVICE_ID", "PRIORITY" }) })
@IdClass(IoTAction.IoTActionId.class)
public class IoTAction {

	@Id
	@JoinColumn(name = "ROUTINE_QUERY_ID", updatable = false, nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private RoutineQuery routineQuery;

	@Id
	@JoinColumn(name = "DEVICE_ID", updatable = false, nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Device device;

	@Lob
	@Column(name = "ACTION", nullable = false)
	private Blob action;

	@Column(name = "PRIORITY", nullable = false)
	private long priority;

	public RoutineQuery getRoutineQuery() {
		return routineQuery;
	}

	public void setRoutineQuery(RoutineQuery routineQuery) {
		this.routineQuery = routineQuery;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Blob getAction() {
		return action;
	}

	public void setAction(Blob action) {
		this.action = action;
	}

	public long getPriority() {
		return priority;
	}

	public void setPriority(long priority) {
		this.priority = priority;
	}

	static class IoTActionId implements Serializable {

		private RoutineQuery routineQuery;
		private Device device;
	}
}
