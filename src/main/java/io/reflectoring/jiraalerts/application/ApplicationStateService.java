package io.reflectoring.jiraalerts.application;

import static io.reflectoring.jiraalerts.application.ApplicationState.NOT_INITIALIZED;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

/**
 * Holds the application state. This service is application-scoped, so you can access the application-state from the whole application.
 */
@ApplicationScope
@Service
public class ApplicationStateService {

	private ApplicationState applicationState = NOT_INITIALIZED;

	public void setApplicationState(ApplicationState applicationState) {
		this.applicationState = applicationState;
	}

	public ApplicationState getApplicationState() {
		return applicationState;
	}
}
