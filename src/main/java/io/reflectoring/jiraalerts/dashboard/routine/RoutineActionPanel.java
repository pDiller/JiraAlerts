package io.reflectoring.jiraalerts.dashboard.routine;

import static io.reflectoring.jiraalerts.dashboard.routine.RoutineQueryState.ACTIVE;
import static io.reflectoring.jiraalerts.dashboard.routine.RoutineQueryState.NOT_ACTIVE;

import java.util.Optional;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

/**
 * Panel for actions with the RoutineQuery.
 */
class RoutineActionPanel extends GenericPanel<RoutineQueryDTO> {

	@Inject
	private RoutineQueryService routineQueryService;

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

		add(new AjaxFallbackLink<Void>("activateRoutineQueryLink") {

			@Override
			public void onClick(Optional<AjaxRequestTarget> targetOptional) {
				routineQueryService.activateRoutineQuery(RoutineActionPanel.this.getModelObject());
				targetOptional.ifPresent(target -> target.add(getPage()));
			}

			@Override
			protected void onConfigure() {
				super.onConfigure();
				RoutineQueryDTO routineQueryDTO = RoutineActionPanel.this.getModelObject();
				setVisible(routineQueryDTO.getRoutineQueryState() == NOT_ACTIVE);
			}
		});

		add(new AjaxFallbackLink<Void>("deactivateRoutineQueryLink") {

			@Override
			public void onClick(Optional<AjaxRequestTarget> targetOptional) {
				routineQueryService.deactivateRoutineQuery(RoutineActionPanel.this.getModelObject());
				targetOptional.ifPresent(target -> target.add(getPage()));
			}

			@Override
			protected void onConfigure() {
				super.onConfigure();
				RoutineQueryDTO routineQueryDTO = RoutineActionPanel.this.getModelObject();
				setVisible(routineQueryDTO.getRoutineQueryState() == ACTIVE);
			}
		});
	}
}
