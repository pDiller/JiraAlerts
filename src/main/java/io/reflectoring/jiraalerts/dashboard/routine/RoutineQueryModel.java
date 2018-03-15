package io.reflectoring.jiraalerts.dashboard.routine;

import javax.inject.Inject;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * Loads the {@link RoutineQueryDTO} with the given id.
 */
class RoutineQueryModel extends LoadableDetachableModel<RoutineQueryDTO> {

	@Inject
	private RoutineQueryService routineQueryService;

	private long routineQueryId;

	/**
	 * Constructor.
	 *
	 * @param routineQueryId
	 *            id.
	 */
	RoutineQueryModel(long routineQueryId) {
		this.routineQueryId = routineQueryId;
		Injector.get().inject(this);
	}

	@Override
	protected RoutineQueryDTO load() {
		return routineQueryService.loadRoutineQueryDTOById(routineQueryId);
	}
}
