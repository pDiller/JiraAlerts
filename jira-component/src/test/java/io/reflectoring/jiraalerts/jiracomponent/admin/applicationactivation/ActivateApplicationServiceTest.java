package io.reflectoring.jiraalerts.jiracomponent.admin.applicationactivation;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class ActivateApplicationServiceTest {

	private ActivateApplicationService sut = new ActivateApplicationService();

	@Test
	public void onDefaultApplicationIsLocked() throws Exception {

		assertThat(sut.isApplicationActivated()).isFalse();
	}

	@Test
	public void activateApplicationActivatesTheApplication() throws Exception {
		sut.activateApplication();

		assertThat(sut.isApplicationActivated()).isTrue();
	}
}
