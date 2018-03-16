package io.reflectoring.jiraalerts.dashboard.routine;

import static io.reflectoring.jiraalerts.dashboard.routine.RoutineQueryState.ACTIVE;
import static io.reflectoring.jiraalerts.dashboard.routine.RoutineQueryState.NOT_ACTIVE;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(RoutineQueryService.class);

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
	 * Saves the {@link RoutineQuery} when the given user exists.
	 *
	 * @param routineQueryDTO
	 *            given routine data.
	 * @param userId
	 *            loggedin user.
	 */
	public void saveRoutineQuery(RoutineQueryDTO routineQueryDTO, long userId) {
		User user = checkifUserExists(userId);

		RoutineQuery routineQuery = new RoutineQuery();
		mapFromDTOToEntity(routineQueryDTO, routineQuery, user);

        RoutineQuery savedRoutineQuery = routineQueryRepository.saveAndFlush(routineQuery);

        LOGGER.info("Saved new RoutineQuery with Id {}", savedRoutineQuery.getId());
	}

	private User checkifUserExists(long userId) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			throw new UnauthorizedActionException(String.format("There is no user with Id %s", userId));
		}
		return user;
	}

	private void mapFromDTOToEntity(RoutineQueryDTO routineQueryDTO, RoutineQuery routineQuery) {
		mapFromDTOToEntity(routineQueryDTO, routineQuery, null);
	}

	private void mapFromDTOToEntity(RoutineQueryDTO routineQueryDTO, RoutineQuery routineQuery, User user) {
		if (routineQueryDTO.getId() != null) {
			routineQuery.setId(routineQueryDTO.getId());
		}
		if (user != null) {
			routineQuery.setOwner(user);
		}
		if (routineQueryDTO.getRoutineQueryState() == null) {
			routineQuery.setRoutineQueryState(ACTIVE);
		} else {
			routineQuery.setRoutineQueryState(routineQueryDTO.getRoutineQueryState());
		}

		routineQuery.setName(routineQueryDTO.getName());
		routineQuery.setJql(routineQueryDTO.getJqlString());
		routineQuery.setMinutesForRecognition(routineQueryDTO.getMinutesForRecognition());
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

	/**
	 * Loads a RoutineQueryDTO by the given routineQueryId.
	 *
	 * @param routineQueryId
	 *            the Id which is used to load the RoutineQuery.
	 * @throws IllegalArgumentException
	 *             when no entity is found.
	 */
	public RoutineQueryDTO loadRoutineQueryDTOById(long routineQueryId) {
		RoutineQuery routineQuery = routineQueryRepository.findOne(routineQueryId);
		if (routineQuery != null) {
			return mapFromEntityToDTO(routineQuery);
		}
		throw new IllegalArgumentException(MessageFormat.format("Error on loading RoutineQuery with id {0,number,##########}", routineQueryId));
	}

	/**
	 * Updates the {@link RoutineQuery} when the given User exists.
	 *
	 * @param routineQueryDTO
	 *            the {@link RoutineQuery} which should be persisted.
	 */
	public void updateRoutineQuery(RoutineQueryDTO routineQueryDTO) {

		RoutineQuery routineQueryForUpdate = routineQueryRepository.findOne(routineQueryDTO.getId());

		mapFromDTOToEntity(routineQueryDTO, routineQueryForUpdate);

        RoutineQuery updatedRoutineQuery = routineQueryRepository.saveAndFlush(routineQueryForUpdate);

        LOGGER.info("Updated RoutineQuery with Id {}", updatedRoutineQuery.getId());

	}

	/**
	 * Acitvates the given {@link RoutineQuery}.
	 */
	public void activateRoutineQuery(RoutineQueryDTO routineQueryDTO) {
		routineQueryDTO.setRoutineQueryState(ACTIVE);
		updateRoutineQuery(routineQueryDTO);
	}

	/**
	 * Deactivates the given {@link RoutineQuery}
	 */
	public void deactivateRoutineQuery(RoutineQueryDTO routineQueryDTO) {
		routineQueryDTO.setRoutineQueryState(NOT_ACTIVE);
		updateRoutineQuery(routineQueryDTO);
	}
}
