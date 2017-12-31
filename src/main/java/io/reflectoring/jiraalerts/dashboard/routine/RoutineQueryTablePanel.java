package io.reflectoring.jiraalerts.dashboard.routine;

import org.apache.wicket.markup.html.panel.Panel;

/**
 * Shows the table for routines and the button to create new routine.
 */
public class RoutineQueryTablePanel extends Panel {

    /**
     * Constructor.
     *
     * @param id     Wicket-ID.
     * @param userId the Id of the user which is loggedin.
     */
    public RoutineQueryTablePanel(String id, long userId) {
        super(id);

        add(new RoutineQueryTable("routineQueryTable", userId));
    }
}
