package io.reflectoring.jiraalerts.application.state;

import static io.reflectoring.jiraalerts.application.state.ApplicationState.ACTIVE;
import static io.reflectoring.jiraalerts.application.state.ApplicationState.NOT_ACTIVE;
import static io.reflectoring.jiraalerts.application.state.ApplicationState.NOT_INITIALIZED;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.dashboard.applicationstate.JiraConnection;
import io.reflectoring.jiraalerts.dashboard.applicationstate.JiraConnectionRepository;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationStateServiceTest {

	@Mock
	private JiraConnectionRepository jiraConnectionRepositoryMock;

	@InjectMocks
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

	@Test
	public void whenRepositoryNotFindAnyConfigurationApplicationStateNotInitialized() throws Exception {
		when(jiraConnectionRepositoryMock.findAll()).thenReturn(emptyList());

		testSubject.initializeApplicationState();

		assertThat(testSubject.getApplicationState()).isEqualTo(NOT_INITIALIZED);
	}

	@Test
	public void whenRepositoryFindsAnyConfigurationApplicationStateNotActive() throws Exception {
		when(jiraConnectionRepositoryMock.findAll()).thenReturn(singletonList(new JiraConnection()));

		testSubject.initializeApplicationState();

		assertThat(testSubject.getApplicationState()).isEqualTo(NOT_ACTIVE);
	}
}
