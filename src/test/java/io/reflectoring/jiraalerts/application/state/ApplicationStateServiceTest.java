package io.reflectoring.jiraalerts.application.state;

import static io.reflectoring.jiraalerts.application.state.ApplicationState.ACTIVE;
import static io.reflectoring.jiraalerts.application.state.ApplicationState.NOT_INITIALIZED;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ApplicationStateServiceTest {

	private ApplicationStateService testSubject = new ApplicationStateService();

	@Test
	public void onDefaultApplicationStateIsNotInitialized() throws Exception {
		assertThat(testSubject.getApplicationState()).isEqualTo(NOT_INITIALIZED);
	}

	@Test
	public void applicationStateCanBeSet() throws Exception {
		testSubject.setApplicationState(ACTIVE);
		assertThat(testSubject.getApplicationState()).isEqualTo(ACTIVE);
	}
}
