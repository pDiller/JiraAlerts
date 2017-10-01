package io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FirstConfigurationServiceTest {

	private static final Date CONFIGURED_AT = new Date();

	@Mock
	private FirstConfigurationRepository firstConfigurationRepositoryMock;

	@Mock
	private FirstConfigurationMapper firstConfigurationMapperMock;

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

	@Test
	public void saveFirstConfigurationSetsConfiguredAtToNow() throws Exception {
		FirstConfigurationDTO firstConfigurationDTO = new FirstConfigurationDTO();
		FirstConfiguration firstConfiguration = new FirstConfiguration();
		when(firstConfigurationMapperMock.dtoToEntity(firstConfigurationDTO)).thenReturn(firstConfiguration);
		when(firstConfigurationRepositoryMock.save(firstConfiguration)).thenReturn(firstConfiguration);
		FirstConfiguration savedFirstConfiguration = sut.saveFirstConfiguration(firstConfigurationDTO);

		assertThat(savedFirstConfiguration.getConfiguredAt()).isAfter(CONFIGURED_AT);
	}

	@Test
	public void saveFirstConfigurationCallsRepositoryToSave() throws Exception {
		FirstConfigurationDTO firstConfigurationDTO = new FirstConfigurationDTO();
		FirstConfiguration firstConfiguration = new FirstConfiguration();
		when(firstConfigurationMapperMock.dtoToEntity(firstConfigurationDTO)).thenReturn(firstConfiguration);

		sut.saveFirstConfiguration(firstConfigurationDTO);

		verify(firstConfigurationRepositoryMock).save(firstConfiguration);
	}

	@Test
	public void saveFirstConfigurationCallsMapper() throws Exception {
		FirstConfigurationDTO firstConfigurationDTO = new FirstConfigurationDTO();
		FirstConfiguration firstConfiguration = new FirstConfiguration();
		when(firstConfigurationMapperMock.dtoToEntity(firstConfigurationDTO)).thenReturn(firstConfiguration);
		sut.saveFirstConfiguration(firstConfigurationDTO);

		verify(firstConfigurationMapperMock).dtoToEntity(firstConfigurationDTO);
	}
}
