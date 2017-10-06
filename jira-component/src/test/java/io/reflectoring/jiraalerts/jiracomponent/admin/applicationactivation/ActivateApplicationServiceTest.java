package io.reflectoring.jiraalerts.jiracomponent.admin.applicationactivation;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration.JiraConnectionDataService;

@RunWith(MockitoJUnitRunner.class)
public class ActivateApplicationServiceTest {

	private static final String ACTIVATION_PASSWORD = "activation";

	@Mock
	private JiraConnectionDataService jiraConnectionDataServiceMock;

	@InjectMocks
	private ActivateApplicationService sut = new ActivateApplicationService();

	@Test
	public void onDefaultApplicationIsLocked() throws Exception {

		assertThat(sut.isApplicationActivated()).isFalse();
	}

	@Test
	public void activateApplicationActivatesTheApplication() throws Exception {
		sut.activateApplication(ACTIVATION_PASSWORD);

		assertThat(sut.isApplicationActivated()).isTrue();
	}

	@Test
	public void activateApplicationLoadsJiraConnectionData() throws Exception {
		sut.activateApplication(ACTIVATION_PASSWORD);

		verify(jiraConnectionDataServiceMock).getJiraConnectionDataDTO();
	}

	@Test
	public void getActivationPasswordReturnsTheGivenPassword() throws Exception {
		sut.activateApplication(ACTIVATION_PASSWORD);

		assertThat(sut.getActivationPassword()).isEqualTo(ACTIVATION_PASSWORD);
	}
}
