package io.reflectoring.jiraalerts.jiracomponent.configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.jiracomponent.configuration.persistence.JiraConnectionData;
import io.reflectoring.jiraalerts.jiracomponent.configuration.persistence.JiraConnectionDataRepository;

@RunWith(MockitoJUnitRunner.class)
public class JiraConnectionConfigurationServiceTest {

	private static final long JIRA_CONNECTION_DATA_ID = 42L;
	private static final long UNKNOWN_JIRA_CONNECTION_DATA_ID = 1337L;
	private static final Date JIRA_CONNECTION_DATA_MODIFIED_DATE = new Date();
	private static final String JIRA_CONNECTION_DATA_URL = "MyFunnyUrl.com/jira/rest";
	private static final String NEW_JIRA_CONNECTION_DATA_URL = "newUrl.com/jira/rest";

	@Mock
	private JiraConnectionDataRepository jiraConnectionDataRepositoryMock;

	@Mock
	private JiraConnectionDataMapper jiraConnectionDataMapperMock;

	@InjectMocks
	private JiraConnectionConfigurationService sut = new JiraConnectionConfigurationService();

	private JiraConnectionData jiraConnectionData;

	@Before
	public void setup() {
		jiraConnectionData = new JiraConnectionData();
		jiraConnectionData.setUrl(JIRA_CONNECTION_DATA_URL);
		jiraConnectionData.setId(JIRA_CONNECTION_DATA_ID);
		jiraConnectionData.setModifiedAt(JIRA_CONNECTION_DATA_MODIFIED_DATE);
		when(jiraConnectionDataRepositoryMock.findOne(JIRA_CONNECTION_DATA_ID)).thenReturn(jiraConnectionData);
	}

	@Test
	public void loadConnectionDataReturnsCorrectConnectionData() {
		JiraConnectionDataDTO jiraConnectionDataDTO = new JiraConnectionDataDTO();
		jiraConnectionDataDTO.setId(JIRA_CONNECTION_DATA_ID);
		when(jiraConnectionDataMapperMock.entityToDTO(jiraConnectionData)).thenReturn(jiraConnectionDataDTO);

		JiraConnectionDataDTO loadedConnectionData = sut.loadJiraConnectionData(JIRA_CONNECTION_DATA_ID);

		assertThat(loadedConnectionData.getId()).isEqualTo(JIRA_CONNECTION_DATA_ID);
	}

	@Test
	public void loadConnectionDataThrowsExceptionWhenRepositoryReturnNull() throws Exception {
		try {
			sut.loadJiraConnectionData(UNKNOWN_JIRA_CONNECTION_DATA_ID);
			failBecauseExceptionWasNotThrown(IllegalStateException.class);
		} catch (IllegalStateException illegalStateException) {
			assertThat(illegalStateException).hasMessage("No configuration found for id: 1337");
		}
	}

	@Test
	public void loadConnectionDataCallsRepositoryForLoadingConnectionData() throws Exception {
		sut.loadJiraConnectionData(JIRA_CONNECTION_DATA_ID);

		verify(jiraConnectionDataRepositoryMock).findOne(JIRA_CONNECTION_DATA_ID);
	}

	@Test
	public void saveConnectionDataSetsLastModified() throws Exception {
		JiraConnectionDataDTO newJiraConnectionData = new JiraConnectionDataDTO();
		sut.saveConnectionData(newJiraConnectionData);

		assertThat(newJiraConnectionData.getModifiedAt()).isAfter(JIRA_CONNECTION_DATA_MODIFIED_DATE);
	}

	@Test
	public void saveConnectionDataCallsRepositoryForSavingConnectionData() throws Exception {
		JiraConnectionDataDTO newJiraConnectionData = new JiraConnectionDataDTO();
		sut.saveConnectionData(newJiraConnectionData);

		verify(jiraConnectionDataRepositoryMock).save(any(JiraConnectionData.class));
	}
}
