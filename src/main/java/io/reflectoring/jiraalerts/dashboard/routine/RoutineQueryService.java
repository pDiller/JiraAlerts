package io.reflectoring.jiraalerts.dashboard.routine;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicemethods for storing and loading the {@link RoutineQuery}.
 */
@Service
@Transactional
public class RoutineQueryService {

	@Inject
	private RoutineQueryRepository routineQueryRepository;

	public int countRoutineQueriesByUserId(long userId) {
		return routineQueryRepository.countByOwner(userId);
	}

	public List<RoutineQueryDTO> getRoutineQueriesByUserId(long userId, PageRequest pageRequest) {
		List<RoutineQuery> routineQueries = routineQueryRepository.findByOwner(userId, pageRequest);
		return mapAllFromEntityToDTO(routineQueries);
	}

	private List<RoutineQueryDTO> mapAllFromEntityToDTO(List<RoutineQuery> routineQueries) {
		List<RoutineQueryDTO> routineQueryDTOs = new ArrayList<>();
		routineQueries.forEach(routineQuery -> routineQueryDTOs.add(mapFromEntityToDTO(routineQuery)));
		return routineQueryDTOs;
	}

	private RoutineQueryDTO mapFromEntityToDTO(RoutineQuery routineQuery) {
		RoutineQueryDTO routineQueryDTO = new RoutineQueryDTO();
		routineQueryDTO.setId(routineQuery.getId());
		routineQueryDTO.setName(routineQuery.getName());
		routineQueryDTO.setJqlString(routineQuery.getJql());
		routineQueryDTO.setMinutesForRecognition(routineQuery.getMinutesForRecognition());
		routineQueryDTO.setRoutineQueryState(routineQuery.getRoutineQueryState());
		return routineQueryDTO;
	}
}
