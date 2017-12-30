package io.reflectoring.jiraalerts.dashboard.routine;

import io.reflectoring.jiraalerts.common.EnumWithId;

/**
 * The state of one Query-Routine.
 */
public enum RoutineQueryState implements EnumWithId {

    /**
     * Active, should be set by user.
     */
    ACTIVE(0),
    /**
     * Not active, default.
     */
    NOT_ACTIVE(1),
    /**
     * If an error occures on the execution of jql, the routine goes in this state.
     */
    ERROR(2);

    private final int id;

    RoutineQueryState(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}
