package io.reflectoring.jiraalerts.dashboard.routine;

import javax.inject.Inject;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * //TODO JavaDoc, Test
 */
class RoutineQueryModel extends LoadableDetachableModel<RoutineQueryDTO> {

	@Inject
	private RoutineQueryService routineQueryService;

	private long routineQueryId;

	RoutineQueryModel(long routineQueryId) {
		this.routineQueryId = routineQueryId;
		Injector.get().inject(this);
	}

	@Override
	protected RoutineQueryDTO load() {
		return routineQueryService.loadRoutineQueryDTOById(routineQueryId);
	}
}
