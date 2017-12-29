package io.reflectoring.jiraalerts.common;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * Shows a {@link org.apache.wicket.markup.html.basic.Label} with a value in a row.
 */
public class FormControlLabelPanel extends Panel {

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-ID.
	 * @param valueModel
	 *            the value to be displayed.
	 * @param labelModel
	 *            the label to be displayed.
	 */
	public FormControlLabelPanel(String id, IModel<String> valueModel, IModel<String> labelModel) {
		super(id);

		add(new Label("label", labelModel));
		add(new Label("input", valueModel));
	}
}
