package io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration;

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
	private JiraConnectionData jiraConnectionData;

	@Before
	public void setup() {
		jiraConnectionDataDTO = new JiraConnectionDataDTO();
		jiraConnectionData = new JiraConnectionData();
		when(jiraConnectionDataMapperMock.dtoToEntity(jiraConnectionDataDTO)).thenReturn(jiraConnectionData);
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
		JiraConnectionData jiraConnectionData = sut.saveJiraConnectionData(jiraConnectionDataDTO);

		assertThat(jiraConnectionData.getModifiedAt()).isAfter(MODIFIED_AT_DATE).isNotNull();
	}
}
