package io.reflectoring.jiraalerts.application;

import static io.reflectoring.jiraalerts.application.ApplicationStatus.NOT_INITIALIZED;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

/**
 * Holds the application state. This service is application-scoped, so you can access the application-state from the whole application.
 */
@ApplicationScope
@Service
public class ApplicationStatusService {

	private ApplicationStatus applicationStatus = NOT_INITIALIZED;

	public void setApplicationStatus(ApplicationStatus applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public ApplicationStatus getApplicationStatus() {
		return applicationStatus;
	}
}
