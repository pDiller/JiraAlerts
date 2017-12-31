package io.reflectoring.jiraalerts.dashboard.routine;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicemethods for storing and loading the {@link RoutineQuery}.
 */
@Service
@Transactional
public class RoutineQueryService {

    @Inject
    private RoutineQueryRepository routineQueryRepository;

    /**
     * Counts the entries by its owner.
     *
     * @param userId the Id of a user which entries should be counted.
     * @return count of entries of the given user.
     */
    public int countRoutineQueriesByUserId(long userId) {
        return routineQueryRepository.countByOwner(userId);
    }

    /**
     * @param userId      the Id of a user which entries should be counted.
     * @param pageRequest the pagable for the repository-call.
     * @return the entries of given user.
     */
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
