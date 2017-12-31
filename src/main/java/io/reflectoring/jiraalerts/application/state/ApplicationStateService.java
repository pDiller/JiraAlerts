package io.reflectoring.jiraalerts.application.state;

import static io.reflectoring.jiraalerts.application.state.ApplicationState.NOT_INITIALIZED;
import static java.lang.String.format;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import io.reflectoring.jiraalerts.dashboard.applicationstate.JiraConnection;
import io.reflectoring.jiraalerts.dashboard.applicationstate.JiraConnectionRepository;

/**
 * Holds the application state. This service is application-scoped, so you can access the application-state from the whole application.
 */
@ApplicationScope
@Service
public class ApplicationStateService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationStateService.class);

	@Inject
	private JiraConnectionRepository jiraConnectionRepository;

	private ApplicationState applicationState = NOT_INITIALIZED;

	private String jiraPassword;

	public void setApplicationState(ApplicationState applicationState) {
		String stateChangeInformation = format("Statechange from '%s' to '%s'", this.applicationState, applicationState);
		LOGGER.info(stateChangeInformation);
		this.applicationState = applicationState;
	}

	public ApplicationState getApplicationState() {
		return applicationState;
	}

	public void initializeApplicationState() {
		List<JiraConnection> jiraConnections = jiraConnectionRepository.findAll();
		if (!jiraConnections.isEmpty()) {
			setApplicationState(ApplicationState.NOT_ACTIVE);
		} else {
			setApplicationState(ApplicationState.NOT_INITIALIZED);
		}
	}

	public String getJiraPassword() {
		return jiraPassword;
	}

	public void setJiraPassword(String jiraPassword) {
		this.jiraPassword = jiraPassword;
	}
}
