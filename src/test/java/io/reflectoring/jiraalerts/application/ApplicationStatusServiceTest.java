package io.reflectoring.jiraalerts.application;

import static io.reflectoring.jiraalerts.application.ApplicationStatus.ACTIVE;
import static io.reflectoring.jiraalerts.application.ApplicationStatus.NOT_INITIALIZED;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ApplicationStatusServiceTest {

	private ApplicationStatusService testSubject = new ApplicationStatusService();

	@Test
	public void onDefaultApplicationStateIsNotInitialized() throws Exception {
		assertThat(testSubject.getApplicationStatus()).isEqualTo(NOT_INITIALIZED);
	}

	@Test
	public void applicationStateCanBeSet() throws Exception {
		testSubject.setApplicationStatus(ACTIVE);
		assertThat(testSubject.getApplicationStatus()).isEqualTo(ACTIVE);
	}
}
