package io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JiraConnectionDataServiceTest {

	private static final Date MODIFIED_AT_DATE = new Date();

	@Mock
	private JiraConnectionDataDTOMapper jiraConnectionDataMapperMock;

	@Mock
	private JiraConnectionDataRepository jiraConnectionDataRepositoryMock;

	@InjectMocks
	private JiraConnectionDataService sut = new JiraConnectionDataService();

	private JiraConnectionDataDTO jiraConnectionDataDTO;
	private JiraConnectionDataDTO modifiedJiraConnectionDataDTO;
	private JiraConnectionData jiraConnectionData;

	@Before
	public void setup() {
		jiraConnectionDataDTO = new JiraConnectionDataDTO();
		jiraConnectionData = new JiraConnectionData();
		modifiedJiraConnectionDataDTO = new JiraConnectionDataDTO();
		modifiedJiraConnectionDataDTO.setModifiedAt(MODIFIED_AT_DATE);
		when(jiraConnectionDataMapperMock.dtoToEntity(jiraConnectionDataDTO)).thenReturn(jiraConnectionData);
		when(jiraConnectionDataMapperMock.entityToDTO(jiraConnectionData)).thenReturn(modifiedJiraConnectionDataDTO);
		when(jiraConnectionDataRepositoryMock.save(jiraConnectionData)).thenReturn(jiraConnectionData);
	}

	@Test
	public void serviceCallsRepositoryToSaveData() {
		sut.saveJiraConnectionData(jiraConnectionDataDTO);

		verify(jiraConnectionDataRepositoryMock).save(any(JiraConnectionData.class));
	}

	@Test
	public void dtoIsMappedToEntityBeforeStored() {
		sut.saveJiraConnectionData(jiraConnectionDataDTO);

		verify(jiraConnectionDataMapperMock).dtoToEntity(jiraConnectionDataDTO);
	}

	@Test
	public void modifiedAtIsSetToEntity() {
		JiraConnectionDataDTO loadedJiraConnectionDataDTO = sut.saveJiraConnectionData(jiraConnectionDataDTO);

		assertThat(loadedJiraConnectionDataDTO.getModifiedAt()).isEqualTo(MODIFIED_AT_DATE).isNotNull();
	}

	@Test
	public void isFirstConfigurationReturnsFalseWhenRepositoryFindsAnyEntries() {
		when(jiraConnectionDataRepositoryMock.findAll()).thenReturn(singletonList(new JiraConnectionData()));

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

		verify(jiraConnectionDataRepositoryMock).findAll();
	}

	@Test
	public void getJiraConnectionDataReturnsMappedJiraConnectionData() throws Exception {
		when(jiraConnectionDataRepositoryMock.findAll()).thenReturn(singletonList(jiraConnectionData));
		JiraConnectionDataDTO loadedJiraConnectionDataDTO = sut.getJiraConnectionDataDTO();

		assertThat(loadedJiraConnectionDataDTO).isEqualTo(modifiedJiraConnectionDataDTO);
	}
}
