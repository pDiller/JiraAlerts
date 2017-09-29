package io.reflectoring.jiraalerts.integration.admin;

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

	private static final Long ZERO = 0L;

	@Mock
	private FirstConfigurationRepository firstConfigurationRepositoryMock;

	@InjectMocks
	private IsFirstConfigurationService sut = new IsFirstConfigurationService();

	@Test
	public void isFirstConfigurationReturnsTrueWhenRepositoryReturnsNull() throws Exception {
		boolean isFirstConfiguration = sut.isFirstConfiguration();

		assertThat(isFirstConfiguration).isTrue();
	}

	@Test
	public void isFirstConfigurationReturnsFalseWhenRepositoryCallFindsEntry() throws Exception {
		FirstConfiguration firstConfiguration = new FirstConfiguration();
		when(firstConfigurationRepositoryMock.findOne(ZERO)).thenReturn(firstConfiguration);
		boolean isFirstConfiguration = sut.isFirstConfiguration();

		assertThat(isFirstConfiguration).isFalse();
	}
}
