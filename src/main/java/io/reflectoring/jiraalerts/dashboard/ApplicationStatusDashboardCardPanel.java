package io.reflectoring.jiraalerts.dashboard;

import org.apache.wicket.model.IModel;

/**
 * Dashboard-Card for application status.
 */
public class ApplicationStatusDashboardCardPanel extends AbstractDashboardCardPanel<String> {

    /**
     * Constructor.
     *
     * @param id    Wicket-ID.
     * @param model Wicket-Model.
     */
    public ApplicationStatusDashboardCardPanel(String id, IModel<String> model) {
        super(id, model);
    }
}
