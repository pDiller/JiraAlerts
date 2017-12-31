package io.reflectoring.jiraalerts.dashboard.routine;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

/**
 * Shows the table for routines and the button to create new routine.
 */
public class RoutineQueryTablePanel extends GenericPanel<RoutineQueryDTO> {

    /**
     * Constructor.
     *
     * @param id                   Wicket-ID.
     * @param routineQueryDTOModel Model to create a new routine.
     * @param userId               the Id of the user which is loggedin.
     */
    public RoutineQueryTablePanel(String id, IModel<RoutineQueryDTO> routineQueryDTOModel, long userId) {
        super(id, routineQueryDTOModel);

        add(new RoutineQueryTable("routineQueryTable", userId));

        add(new BookmarkablePageLink<>("createNewRoutineQueryLink", CreateRoutineQueryPage.class));
    }
}
