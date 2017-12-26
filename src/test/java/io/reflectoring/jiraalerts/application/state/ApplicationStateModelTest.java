package io.reflectoring.jiraalerts.application.state;

import static io.reflectoring.jiraalerts.application.state.ApplicationState.ACTIVE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.application.TestApplication;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationStateModelTest {

	@Mock
	private ApplicationStateService applicationStateServiceMock;

	private ApplicationStateModel testSubject;

	@Before
	public void setUp() throws Exception {
		new WicketTester(new TestApplication(this));
		testSubject = new ApplicationStateModel();
	}

	@Test
	public void getObjectReturnsStateFromService() throws Exception {
		when(applicationStateServiceMock.getApplicationState()).thenReturn(ACTIVE);

		ApplicationState loadedApplicationState = testSubject.getObject();

		assertThat(loadedApplicationState).isEqualTo(ACTIVE);
	}

	@Test
	public void getObjectCallsService() throws Exception {
		testSubject.getObject();

		verify(applicationStateServiceMock).getApplicationState();
	}
}
