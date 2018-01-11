package io.reflectoring.jiraalerts.dashboard;

import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

/**
 * Abstract Panel with basic-layout for the card. A implementation of this class has to provide following propert-keys in it´s class.properties file:
 * <ul>
 * <li>card.header.text</li>
 * <li>card.body.text</li>
 * </ul>
 *
 * @param <T>
 *            Modelobjects type.
 */
public abstract class AbstractDashboardCardPanel<T> extends GenericPanel<T> {

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-ID.
	 * @param model
	 *            Wicket-Model.
	 */
	public AbstractDashboardCardPanel(String id, IModel<T> model) {
		super(id, model);
	}

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-ID.
	 */
	public AbstractDashboardCardPanel(String id) {
		super(id);
	}
}
