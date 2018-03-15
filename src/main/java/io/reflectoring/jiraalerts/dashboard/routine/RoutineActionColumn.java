package io.reflectoring.jiraalerts.dashboard.routine;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Column to show the actions for RoutineQueries.
 */
class RoutineActionColumn extends AbstractColumn<RoutineQueryDTO, String> {

	/**
	 * Constructor.
	 */
	RoutineActionColumn() {
		super(Model.of(EMPTY));
	}

	@Override
	public void populateItem(Item<ICellPopulator<RoutineQueryDTO>> cellItem, String componentId, IModel<RoutineQueryDTO> rowModel) {
		cellItem.add(new RoutineActionPanel(componentId, rowModel));
	}
}
