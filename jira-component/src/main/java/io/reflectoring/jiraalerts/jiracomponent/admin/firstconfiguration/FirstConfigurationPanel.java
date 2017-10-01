package io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration;

import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

public class FirstConfigurationPanel extends GenericPanel<JiraConnectionDataDTO> {

	public FirstConfigurationPanel(String id, IModel<JiraConnectionDataDTO> jiraConnectionDataDTOModel) {
		super(id, jiraConnectionDataDTOModel);
	}
}
