package io.reflectoring.jiraalerts.dashboard;

import org.apache.wicket.model.IModel;

import io.reflectoring.jiraalerts.applicationstatus.JiraLoginDTO;
import io.reflectoring.jiraalerts.applicationstatus.SetupApplicationPanel;

/**
 * Dashboard-Card for application status.
 */
public class ApplicationStatusDashboardCardPanel extends AbstractDashboardCardPanel<JiraLoginDTO> {

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-ID.
	 * @param jiraLoginDTOModel
	 *            Wicket-Model.
	 */
	public ApplicationStatusDashboardCardPanel(String id, IModel<JiraLoginDTO> jiraLoginDTOModel) {
		super(id, jiraLoginDTOModel);

		add(new SetupApplicationPanel("setupApplicationPanel", getModel()));
	}
}
