package io.reflectoring.jiraalerts.dashboard.routine;

import static io.reflectoring.jiraalerts.dashboard.routine.RoutineQueryState.NOT_ACTIVE;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.RestClientException;

import io.reflectoring.jiraalerts.application.login.User;
import io.reflectoring.jiraalerts.application.login.UserRepository;
import io.reflectoring.jiraalerts.common.UnauthorizedActionException;
import io.reflectoring.jiraalerts.jiraclient.JiraRestClientService;

/**
 * Servicemethods for storing and loading the {@link RoutineQuery}.
 */
@Service
@Transactional
public class RoutineQueryService {

	@Inject
	private RoutineQueryRepository routineQueryRepository;

	@Inject
	private UserRepository userRepository;

	@Inject
	private JiraRestClientService jiraRestClientService;

	/**
	 * Counts the entries by its owner.
	 *
	 * @param userId
	 *            the Id of a user which entries should be counted.
	 * @return count of entries of the given user.
	 */
	public int countRoutineQueriesByUserId(long userId) {
		return routineQueryRepository.countByOwner(userId);
	}

	/**
	 * @param userId
	 *            the Id of a user which entries should be counted.
	 * @param pageRequest
	 *            the pagable for the repository-call.
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

	/**
	 * Saves the {@link RoutineQuery} when the {@link RoutineQueryService#checkJql(String)} doesn´t throw any exception.
	 *
	 * @param routineQueryDTO
	 *            given routine data.
	 * @param userId
	 *            loggedin user.
	 */
	public void saveRoutineQuery(RoutineQueryDTO routineQueryDTO, long userId) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			throw new UnauthorizedActionException(String.format("There is no user with Id %s", userId));
		}

		RoutineQuery routineQuery = new RoutineQuery();
		routineQuery.setName(routineQueryDTO.getName());
		routineQuery.setJql(routineQueryDTO.getJqlString());
		routineQuery.setMinutesForRecognition(routineQueryDTO.getMinutesForRecognition());
		routineQuery.setRoutineQueryState(NOT_ACTIVE);
		routineQuery.setOwner(user);

		routineQueryRepository.save(routineQuery);
	}

	/**
	 * Checks if the jql is valid. Otherwise Exception is thrown.
	 *
	 * @param jql
	 *            the given jql.
	 */
	public boolean checkJql(String jql) {
		try {
			JiraRestClient initializedJiraRestClient = jiraRestClientService.getInitializedJiraRestClient();
			initializedJiraRestClient.getSearchClient().searchJql(jql).claim();
			return true;
		} catch (RestClientException restClientException) {
			return false;
		} catch (Exception exception) {
			throw new CheckJqlException("Jql check failed. Nested exception:", exception);
		}
	}
}
