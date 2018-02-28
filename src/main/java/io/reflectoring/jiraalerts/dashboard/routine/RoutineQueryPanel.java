package io.reflectoring.jiraalerts.dashboard.routine;

import static org.wicketstuff.lazymodel.LazyModel.from;
import static org.wicketstuff.lazymodel.LazyModel.model;

import java.util.Optional;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.validation.validator.RangeValidator;

import io.reflectoring.jiraalerts.common.FormControlTextFieldPanel;

/**
 * Panel, which shows the inputs for create and edit an {@link RoutineQuery}.
 */
class RoutineQueryPanel extends GenericPanel<RoutineQueryDTO> {

	private static final int MIN_VALUE = 1;
	private static final int MAX_VALUE = 60;

	@Inject
	private RoutineQueryService routineQueryService;

	private WebMarkupContainer successIcon;
	private WebMarkupContainer failIcon;

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-ID.
	 * @param model
	 *            the {@link RoutineQueryDTO}.
	 */
	RoutineQueryPanel(String id, IModel<RoutineQueryDTO> model) {
		super(id, model);

		Form<RoutineQueryDTO> routineForm = new Form<>("routineForm", getModel());
		addRoutineNameComponent(routineForm);

		Form<Void> jqlForm = new Form<>("jqlForm");
		addRoutineJqlComponent(jqlForm);
		addJQLTestLink(jqlForm);
		addJQLIcons(jqlForm);
		routineForm.add(jqlForm);

		addRoutineMinutesComponent(routineForm);
		add(routineForm);
	}

	private void addRoutineMinutesComponent(Form<RoutineQueryDTO> routineForm) {
		IModel<String> routineMinutesLabelModel = new ResourceModel("routine.minutes.text");
		IModel<Integer> routineMinutesModel = model(from(RoutineQueryDTO.class).getMinutesForRecognition()).bind(getModel());
		FormControlTextFieldPanel<Integer> routineMinutesPanel = new FormControlTextFieldPanel<>("routineMinutesPanel", routineMinutesModel,
		        routineMinutesLabelModel, true);
		routineMinutesPanel.getInput().add(new RangeValidator<>(MIN_VALUE, MAX_VALUE));
		routineForm.add(routineMinutesPanel);
	}

	private void addRoutineJqlComponent(Form<Void> jqlForm) {
		IModel<String> routineJqlLabelModel = new ResourceModel("routine.jql.text");
		IModel<String> routineJqlModel = model(from(RoutineQueryDTO.class).getJqlString()).bind(getModel());
		FormControlTextFieldPanel<String> routineJqlPanel = new FormControlTextFieldPanel<>("routineJqlPanel", routineJqlModel, routineJqlLabelModel,
		        true);
		routineJqlPanel.getInput().add(new OnChangeAjaxBehavior() {

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				resetJQLIcons(target);
			}
		});
		jqlForm.add(routineJqlPanel);
	}

	private void addJQLTestLink(Form<Void> jqlForm) {
		jqlForm.add(new AjaxFallbackButton("checkJqlLink", jqlForm) {

			@Override
			protected void onSubmit(Optional<AjaxRequestTarget> targetOptional) {
				RoutineQueryDTO routineQueryDTO = RoutineQueryPanel.this.getModelObject();
				boolean checkSuccessFull = routineQueryService.checkJql(routineQueryDTO.getJqlString());
				successIcon.setVisible(checkSuccessFull);
				failIcon.setVisible(!checkSuccessFull);
				targetOptional.ifPresent(target -> target.add(successIcon, failIcon));
			}
		});
	}

	private void addJQLIcons(Form<Void> jqlForm) {
		successIcon = new WebMarkupContainer("successIcon");
		successIcon.setVisible(false);
		successIcon.setOutputMarkupPlaceholderTag(true);
		failIcon = new WebMarkupContainer("failIcon");
		failIcon.setVisible(false);
		failIcon.setOutputMarkupPlaceholderTag(true);
		jqlForm.add(successIcon, failIcon);
	}

	private void resetJQLIcons(AjaxRequestTarget target) {
		successIcon.setVisible(false);
		failIcon.setVisible(false);
		if (target != null) {
			target.add(successIcon, failIcon);
		}
	}

	private void addRoutineNameComponent(Form<RoutineQueryDTO> routineForm) {
		IModel<String> routineNameLabelModel = new ResourceModel("routine.name.text");
		IModel<String> routineNameModel = model(from(RoutineQueryDTO.class).getName()).bind(getModel());
		routineForm.add(new FormControlTextFieldPanel<>("routineNamePanel", routineNameModel, routineNameLabelModel, true));
	}
}
