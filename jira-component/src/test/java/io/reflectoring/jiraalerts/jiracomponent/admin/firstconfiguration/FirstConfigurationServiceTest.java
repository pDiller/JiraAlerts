package io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FirstConfigurationServiceTest {

	@Mock
	private FirstConfigurationRepository firstConfigurationRepositoryMock;

	@InjectMocks
	private FirstConfigurationService sut = new FirstConfigurationService();

	@Test
	public void isFirstConfigurationReturnsFalseWhenRepositoryFindsAnyEntries() {
		when(firstConfigurationRepositoryMock.findAll()).thenReturn(Collections.singletonList(new FirstConfiguration()));

		boolean firstConfiguration = sut.isFirstConfiguration();

		assertThat(firstConfiguration).isFalse();
	}

	@Test
	public void isFirstConfigurationReturnsTrueWhenRepositoryFindsNoEntries() {
		boolean firstConfiguration = sut.isFirstConfiguration();

		assertThat(firstConfiguration).isTrue();
	}

	@Test
	public void isFirstConfigurationAsksRepositoryForEntries() {
		sut.isFirstConfiguration();

		verify(firstConfigurationRepositoryMock).findAll();
	}
}
