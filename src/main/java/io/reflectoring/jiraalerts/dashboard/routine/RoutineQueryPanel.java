package io.reflectoring.jiraalerts.dashboard.routine;

import static org.wicketstuff.lazymodel.LazyModel.from;
import static org.wicketstuff.lazymodel.LazyModel.model;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.validation.validator.RangeValidator;

import io.reflectoring.jiraalerts.common.FormControlTextFieldPanel;

/**
 * Panel, which shows the inputs for create and edit an {@link RoutineQuery}.
 */
public class RoutineQueryPanel extends GenericPanel<RoutineQueryDTO> {

	private static final int MIN_VALUE = 1;
	private static final int MAX_VALUE = 1440;

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-ID.
	 * @param model
	 *            the {@link RoutineQueryDTO}.
	 */
	public RoutineQueryPanel(String id, IModel<RoutineQueryDTO> model) {
		super(id, model);

		Form<RoutineQueryDTO> routineForm = new Form<>("routineForm", getModel());

		addRoutineNameComponent(routineForm);

		addRoutineJqlComponent(routineForm);

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

	private void addRoutineJqlComponent(Form<RoutineQueryDTO> routineForm) {
		IModel<String> routineJqlLabelModel = new ResourceModel("routine.jql.text");
		IModel<String> routineJqlModel = model(from(RoutineQueryDTO.class).getJqlString()).bind(getModel());
		routineForm.add(new FormControlTextFieldPanel<>("routineJqlPanel", routineJqlModel, routineJqlLabelModel, true));
	}

	private void addRoutineNameComponent(Form<RoutineQueryDTO> routineForm) {
		IModel<String> routineNameLabelModel = new ResourceModel("routine.name.text");
		IModel<String> routineNameModel = model(from(RoutineQueryDTO.class).getName()).bind(getModel());
		routineForm.add(new FormControlTextFieldPanel<>("routineNamePanel", routineNameModel, routineNameLabelModel, true));
	}
}
