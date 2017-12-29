package io.reflectoring.jiraalerts.dashboard.applicationstate;

import static org.wicketstuff.lazymodel.LazyModel.from;
import static org.wicketstuff.lazymodel.LazyModel.model;

import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import io.reflectoring.jiraalerts.common.FormControlLabelPanel;

/**
 * Panel for display active application state.
 */
public class LoggedInApplicationPanel extends GenericPanel<JiraLoginDTO> {

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-ID.
	 * @param model
	 *            The initialized connection values for JIRA.
	 */
	public LoggedInApplicationPanel(String id, IModel<JiraLoginDTO> model) {
		super(id, model);

		IModel<String> urlLabelModel = new ResourceModel("jira.url.label.text");
		IModel<String> urlValueModel = model(from(JiraLoginDTO.class).getUrl()).bind(getModel());
		add(new FormControlLabelPanel("urlLabelPanel", urlValueModel, urlLabelModel));

		IModel<String> usernameLabelModel = new ResourceModel("jira.username.label.text");
		IModel<String> usernameValueModel = model(from(JiraLoginDTO.class).getUsername()).bind(getModel());
		add(new FormControlLabelPanel("usernameLabelPanel", usernameValueModel, usernameLabelModel));
	}
}
