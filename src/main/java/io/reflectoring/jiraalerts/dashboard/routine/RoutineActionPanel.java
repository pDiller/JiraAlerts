package io.reflectoring.jiraalerts.dashboard.routine;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

/**
 * Panel for actions with the RoutineQuery.
 */
class RoutineActionPanel extends GenericPanel<RoutineQueryDTO> {

	/**
	 * Constructor.
	 * 
	 * @param id
	 *            Wicket-Id.
	 * @param model
	 *            the model which is used for the actions.
	 */
	RoutineActionPanel(String id, IModel<RoutineQueryDTO> model) {
		super(id, model);
		add(new BookmarkablePageLink<>("editRoutineQueryLink", EditRoutineQueryPage.class,
		        EditRoutineQueryPage.createPageParameters(getModelObject().getId())));
	}
}
