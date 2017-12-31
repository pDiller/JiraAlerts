package io.reflectoring.jiraalerts.dashboard.routine;

import java.util.Iterator;

import javax.inject.Inject;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * provides the existing routines.
 */
public class RoutineQueryDataProvider extends SortableDataProvider<RoutineQueryDTO, String> {

	@Inject
	private RoutineQueryService routineQueryService;

	private long userId;
	private int rowsPerPage;

	/**
	 * Construcotr.
	 *
	 * @param userId
	 *            the Id of the user which is loggedin.
	 * @param rowsPerPage
	 *            the number of entries per page.
	 */
	public RoutineQueryDataProvider(long userId, int rowsPerPage) {
		this.userId = userId;
		this.rowsPerPage = rowsPerPage;
		Injector.get().inject(this);
		setSort("name", SortOrder.DESCENDING);
	}

	@Override
	public Iterator<? extends RoutineQueryDTO> iterator(long first, long count) {
		return routineQueryService.getRoutineQueriesByUserId(userId, createPageRequest(first)).iterator();
	}

	@Override
	public long size() {
		return routineQueryService.countRoutineQueriesByUserId(userId);
	}

	@Override
	public IModel<RoutineQueryDTO> model(RoutineQueryDTO object) {
		return Model.of(object);
	}

	private PageRequest createPageRequest(long first) {
		Sort.Direction sortDirection = getSort().isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC;
		return new PageRequest((int) (first / rowsPerPage), rowsPerPage, sortDirection, getSort().getProperty());
	}
}
