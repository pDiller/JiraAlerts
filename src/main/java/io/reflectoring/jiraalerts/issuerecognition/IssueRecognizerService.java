package io.reflectoring.jiraalerts.issuerecognition;

import static org.apache.commons.lang3.time.DurationFormatUtils.formatDuration;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.google.common.collect.Iterables;

import io.reflectoring.jiraalerts.application.state.ApplicationState;
import io.reflectoring.jiraalerts.application.state.ApplicationStateService;
import io.reflectoring.jiraalerts.dashboard.routine.RoutineQuery;
import io.reflectoring.jiraalerts.dashboard.routine.RoutineQueryRepository;
import io.reflectoring.jiraalerts.jiraclient.JiraRestClientService;

/**
 * Recognizes new issues.
 */
@Service
public class IssueRecognizerService {

	private static final Logger LOGGER = getLogger(IssueRecognizerService.class);

	@Inject
	private JiraRestClientService jiraRestClientService;

	@Inject
	private ApplicationStateService applicationStateService;

	@Inject
	private RoutineQueryRepository routineQueryRepository;

	/**
	 * Asks the interface for new issues.
	 */
	public void recognize() {
		if (applicationStateService.getApplicationState() == ApplicationState.ACTIVE) {
			JiraRestClient initializedJiraRestClient = jiraRestClientService.getInitializedJiraRestClient();
			List<RoutineQuery> routineQueries = routineQueryRepository.findAllActive();

			LOGGER.info("Found {} routine(s)", routineQueries.size());

			routineQueries.forEach(routineQuery -> getIssues(routineQuery, initializedJiraRestClient));
		}
	}

	private void getIssues(RoutineQuery routineQuery, JiraRestClient initializedJiraRestClient) {
		LOGGER.info("Searching for issues with routine id {}", routineQuery.getId());
		StopWatch issueSearchStopWatch = new StopWatch();
		issueSearchStopWatch.start();

		String jql = routineQuery.getJql();
		int issues = Iterables.size(initializedJiraRestClient.getSearchClient().searchJql(jql).claim().getIssues());

		issueSearchStopWatch.stop();
		String formatedDuration = formatDuration(issueSearchStopWatch.getTime(), "HH:mm:ss,SSS");
		LOGGER.info("found {} issue(s) in {}.", issues, formatedDuration);
	}
}
