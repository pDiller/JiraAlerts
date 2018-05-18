package io.reflectoring.jiraalerts.iotaction;

import io.reflectoring.jiraalerts.device.Device;
import io.reflectoring.jiraalerts.routine.RoutineQuery;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;

/**
 * Entity for IoT-Actions.
 */
@Entity
@Table(name = "IOT_ACTION", uniqueConstraints = {@UniqueConstraint(columnNames = {"DEVICE_ID", "PRIORITY"})})
@IdClass(IoTAction.IoTActionId.class)
public class IoTAction implements Serializable {

    @Id
    @JoinColumn(name = "ROUTINE_QUERY_ID", updatable = false, nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private RoutineQuery routineQuery;

    @Id
    @JoinColumn(name = "DEVICE_ID", updatable = false, nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Device device;

    @Column(name = "ACTION", nullable = false)
    private String action;

    @Column(name = "PRIORITY", nullable = false)
    private int priority;

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    static class IoTActionId implements Serializable {

        private RoutineQuery routineQuery;
        private Device device;
    }
}
