package io.reflectoring.jiraalerts.dashboard.routine;

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
import org.apache.wicket.model.Model;

import io.reflectoring.jiraalerts.application.JiraAlertsSession;
import io.reflectoring.jiraalerts.base.BasePage;
import io.reflectoring.jiraalerts.common.FeedbackPanelErrorClassModifier;

@AuthorizeInstantiation("administrator")
public class CreateRoutineQueryPage extends BasePage {

	@Inject
	private RoutineQueryService routineQueryService;

	public CreateRoutineQueryPage() {

		IModel<RoutineQueryDTO> routineQueryDTOModel = new Model<>(new RoutineQueryDTO());
		Form<RoutineQueryDTO> createRoutineForm = new Form<>("createRoutineForm", routineQueryDTOModel);

		createRoutineForm.add(new RoutineQueryPanel("routineQueryPanel", routineQueryDTOModel));

		FeedbackPanel feedbackPanel = new FencedFeedbackPanel("formFeedback", createRoutineForm);
		feedbackPanel.add(new FeedbackPanelErrorClassModifier());
		createRoutineForm.add(feedbackPanel);

		createRoutineForm.add(new AjaxFallbackButton("submitButton", createRoutineForm) {

			@Override
			protected void onSubmit(Optional<AjaxRequestTarget> targetOptional) {
				RoutineQueryDTO routineQueryDTO = routineQueryDTOModel.getObject();
				if (routineQueryService.checkJql(routineQueryDTO.getJqlString())) {
					routineQueryDTO.setRoutineQueryState(RoutineQueryState.ACTIVE);
					long userId = JiraAlertsSession.get().getUserId();
					routineQueryService.saveRoutineQuery(routineQueryDTO, userId);
					setResponsePage(RoutineQueriesDetailPage.class);
				} else {
					createRoutineForm.error(CreateRoutineQueryPage.this.getString("save.error.text"));
					targetOptional.ifPresent(target -> target.add(CreateRoutineQueryPage.this));
				}
			}

			@Override
			protected void onError(Optional<AjaxRequestTarget> targetOptional) {
				targetOptional.ifPresent(target -> target.add(CreateRoutineQueryPage.this));
			}
		});

		createRoutineForm.add(new BookmarkablePageLink<Void>("cancelButton", RoutineQueriesDetailPage.class));

		add(createRoutineForm);
	}
}
