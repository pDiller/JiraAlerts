package io.reflectoring.jiraalerts.integration.admin;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.integration.admin.persistence.FirstConfiguration;
import io.reflectoring.jiraalerts.integration.admin.persistence.FirstConfigurationRepository;

@RunWith(MockitoJUnitRunner.class)
public class IsFirstConfigurationServiceTest {

	@Mock
	private FirstConfigurationRepository firstConfigurationRepositoryMock;

	@InjectMocks
	private FirstConfigurationService sut = new FirstConfigurationService();

	@Test
	public void isFirstConfigurationReturnsTrueWhenRepositoryReturnsNull() throws Exception {
		boolean isFirstConfiguration = sut.isFirstConfiguration();

		assertThat(isFirstConfiguration).isTrue();
	}

	@Test
	public void isFirstConfigurationReturnsFalseWhenRepositoryCallFindsEntry() throws Exception {
		FirstConfiguration firstConfiguration = new FirstConfiguration();
		when(firstConfigurationRepositoryMock.findAll()).thenReturn(singletonList(firstConfiguration));
		boolean isFirstConfiguration = sut.isFirstConfiguration();

		assertThat(isFirstConfiguration).isFalse();
	}
}
