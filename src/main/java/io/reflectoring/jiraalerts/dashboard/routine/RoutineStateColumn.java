package io.reflectoring.jiraalerts.dashboard.routine;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.model.StringResourceModel;

/**
 * Column for show the state as a badge.
 */
class RoutineStateColumn extends PropertyColumn<RoutineQueryDTO, String> {

	/**
	 * Constructor.
	 */
	RoutineStateColumn() {
		super(new ResourceModel("routine.table.state.column"), "routineQueryState", "routineQueryState");
	}

	@Override
	public void populateItem(Item<ICellPopulator<RoutineQueryDTO>> item, String componentId, IModel<RoutineQueryDTO> rowModel) {
		Label label = new Label(componentId, getDataModel(rowModel));
		label.add(new RoutineStateClassModifier(rowModel.getObject().getRoutineQueryState()));
		item.add(label);
	}

	@Override
	public IModel<?> getDataModel(IModel<RoutineQueryDTO> rowModel) {
		return new StringResourceModel("routine.table.state.text.${routineQueryState}", rowModel);
	}
}
