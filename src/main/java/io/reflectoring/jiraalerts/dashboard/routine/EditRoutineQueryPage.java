package io.reflectoring.jiraalerts.dashboard.routine;

import static io.reflectoring.jiraalerts.dashboard.routine.RoutineQueryPageParameters.ROUTINE_ID;
import static io.reflectoring.jiraalerts.routine.RoutineQueryState.ACTIVE;

import java.util.Optional;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.feedback.FencedFeedbackPanel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import io.reflectoring.jiraalerts.base.BasePage;
import io.reflectoring.jiraalerts.common.FeedbackPanelErrorClassModifier;
import io.reflectoring.jiraalerts.routine.RoutineQuery;

/**
 * Page for Editing one {@link RoutineQuery}.
 */
@AuthorizeInstantiation("administrator")
public class EditRoutineQueryPage extends BasePage {

	@Inject
	private RoutineQueryService routineQueryService;

	/**
	 * Constructor.
	 *
	 * @param pageParameters
	 *            {@link PageParameters} with the Id of the {@link RoutineQuery} which should be changed.
	 */
	public EditRoutineQueryPage(PageParameters pageParameters) {

		long routineQueryId = getRoutineQueryId(pageParameters);
		IModel<RoutineQueryDTO> routineQueryDTOModel = new RoutineQueryModel(routineQueryId);

		Form<RoutineQueryDTO> editRoutineForm = new Form<>("editRoutineForm", routineQueryDTOModel);

		editRoutineForm.add(new RoutineQueryPanel("routineQueryPanel", routineQueryDTOModel));

		FeedbackPanel feedbackPanel = new FencedFeedbackPanel("formFeedback", editRoutineForm);
		feedbackPanel.add(new FeedbackPanelErrorClassModifier());
		editRoutineForm.add(feedbackPanel);

		editRoutineForm.add(new AjaxFallbackButton("submitButton", editRoutineForm) {

			@Override
			protected void onSubmit(Optional<AjaxRequestTarget> targetOptional) {
				saveRoutineQuery(targetOptional);
			}

			private void saveRoutineQuery(Optional<AjaxRequestTarget> targetOptional) {
				RoutineQueryDTO routineQueryDTO = routineQueryDTOModel.getObject();
				if (routineQueryService.checkJql(routineQueryDTO.getJqlString())) {
					routineQueryDTO.setRoutineQueryState(ACTIVE);
					routineQueryService.updateRoutineQuery(routineQueryDTO);
					setResponsePage(RoutineQueriesDetailPage.class);
				} else {
					editRoutineForm.error(EditRoutineQueryPage.this.getString("save.error.text"));
					targetOptional.ifPresent(target -> target.add(EditRoutineQueryPage.this));
				}
			}

			@Override
			protected void onError(Optional<AjaxRequestTarget> targetOptional) {
				targetOptional.ifPresent(target -> target.add(EditRoutineQueryPage.this));
			}
		});

		editRoutineForm.add(new BookmarkablePageLink<Void>("cancelButton", RoutineQueriesDetailPage.class));

		add(editRoutineForm);
	}

	private long getRoutineQueryId(PageParameters pageParameters) {
		String routineIdAsString = pageParameters.get(ROUTINE_ID).toString();
		return Long.valueOf(routineIdAsString);
	}

	static PageParameters createPageParameters(long routineQueryId) {
		PageParameters pageParameters = new PageParameters();
		pageParameters.add(ROUTINE_ID, routineQueryId);
		return pageParameters;
	}
}
