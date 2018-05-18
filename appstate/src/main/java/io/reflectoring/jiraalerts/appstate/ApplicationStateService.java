package io.reflectoring.jiraalerts.appstate;

import io.reflectoring.jiraalerts.jiraconnection.JiraConnection;
import io.reflectoring.jiraalerts.jiraconnection.JiraConnectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

import static io.reflectoring.jiraalerts.appstate.ApplicationState.NOT_INITIALIZED;
import static java.lang.String.format;

/**
 * Holds the application state. This service is application-scoped, so you can access the application-state from the whole application.
 */
@ApplicationScope
@Service
public class ApplicationStateService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationStateService.class);

	@Autowired
	private JiraConnectionRepository jiraConnectionRepository;

	private ApplicationState applicationState = NOT_INITIALIZED;

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
}
