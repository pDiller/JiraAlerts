package io.reflectoring.jiraalerts.applicationstatus;

import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

public class LoggedInApplicationPanel extends GenericPanel<String> {

	public LoggedInApplicationPanel(String id, IModel<String> model) {
		super(id, model);
	}
}
