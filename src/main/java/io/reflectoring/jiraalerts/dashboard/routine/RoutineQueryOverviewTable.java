package io.reflectoring.jiraalerts.dashboard.routine;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.ResourceModel;

/**
 * Table to show the existing routines.
 */
public class RoutineQueryOverviewTable extends AjaxFallbackDefaultDataTable<RoutineQueryDTO, String> {

	private static final int ROWS_PER_PAGE = 5;

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-Id.
	 * @param userId
	 *            the Id of the user which is loggedin.
	 */
	public RoutineQueryOverviewTable(String id, long userId) {
		super(id, createColumns(), new RoutineQueryDataProvider(userId, ROWS_PER_PAGE), ROWS_PER_PAGE);
	}

	private static List<? extends IColumn<RoutineQueryDTO, String>> createColumns() {
		List<IColumn<RoutineQueryDTO, String>> columns = new ArrayList<>();
		columns.add(new PropertyColumn<>(new ResourceModel("routine.table.name.column"), "name", "name"));
		columns.add(new PropertyColumn<>(new ResourceModel("routine.table.minutes.column"), "minutesForRecognition", "minutesForRecognition"));
		columns.add(new RoutineStateColumn());
		return columns;
	}
}
