package de.reflectoring.jiraalerts.jiracomponent.configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.reflectoring.jiraalerts.jiracomponent.connection.persistence.JiraConnectionData;
import de.reflectoring.jiraalerts.jiracomponent.connection.persistence.JiraConnectionDataRepository;

@RunWith(MockitoJUnitRunner.class)
public class JiraConnectionConfigurationServiceTest {

    private static final long JIRA_CONNECTION_DATA_ID = 42L;
    private static final long UNKNOWN_JIRA_CONNECTION_DATA_ID = 1337L;
    private static final Date JIRA_CONNECTION_DATA_MODIFIED_DATE = new Date();
    private static final String JIRA_CONNECTION_DATA_URL = "MyFunnyUrl.com/jira/rest";
    private static final String NEW_JIRA_CONNECTION_DATA_URL = "newUrl.com/jira/rest";

    @Mock
    private JiraConnectionDataRepository jiraConnectionDataRepositoryMock;

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
    public void loadConnectionUrlReturnsConnectionUrlFromDataObject() {
        String loadedConnectionUrl = sut.loadConnectionUrl(JIRA_CONNECTION_DATA_ID);

        assertThat(loadedConnectionUrl).isEqualTo(JIRA_CONNECTION_DATA_URL);
    }

    @Test
    public void loadConnectionUrlThrowsExceptionWhenRepositoryReturnNull() throws Exception {
        try {
            sut.loadConnectionUrl(UNKNOWN_JIRA_CONNECTION_DATA_ID);
            failBecauseExceptionWasNotThrown(IllegalStateException.class);
        } catch (IllegalStateException illegalStateException) {
            assertThat(illegalStateException).hasMessage("No configuration found for id: 1337");
        }
    }

    @Test
    public void loadConnectionCallsRepositoryForLoadingData() throws Exception {
        sut.loadConnectionUrl(JIRA_CONNECTION_DATA_ID);

        verify(jiraConnectionDataRepositoryMock).findOne(JIRA_CONNECTION_DATA_ID);
    }

    @Test
    public void saveConnectionUrlSetsNewUrl() throws Exception {
        sut.saveConnectionUrl(JIRA_CONNECTION_DATA_ID, NEW_JIRA_CONNECTION_DATA_URL);

        assertThat(jiraConnectionData.getUrl()).isEqualTo(NEW_JIRA_CONNECTION_DATA_URL);
    }

    @Test
    public void saveConnectionUrlSetsLastModified() throws Exception {
        sut.saveConnectionUrl(JIRA_CONNECTION_DATA_ID, NEW_JIRA_CONNECTION_DATA_URL);

        assertThat(jiraConnectionData.getModifiedAt()).isAfter(JIRA_CONNECTION_DATA_MODIFIED_DATE);
    }

    @Test
    public void saveConnectionUrlThrowsExceptionWhenLoadedConnectionDataIsNull() throws Exception {
        try {
            sut.saveConnectionUrl(UNKNOWN_JIRA_CONNECTION_DATA_ID, NEW_JIRA_CONNECTION_DATA_URL);
            failBecauseExceptionWasNotThrown(IllegalStateException.class);
        } catch (IllegalStateException illegalStateException) {
            assertThat(illegalStateException).hasMessage("No configuration found for id: 1337");
        }
    }

    @Test
    public void saveConnectionUrlLoadsObjectWithGivenIdAndSetDataInSameObject() throws Exception {
        sut.saveConnectionUrl(JIRA_CONNECTION_DATA_ID, NEW_JIRA_CONNECTION_DATA_URL);

        verify(jiraConnectionDataRepositoryMock).findOne(JIRA_CONNECTION_DATA_ID);
        verify(jiraConnectionDataRepositoryMock).save(jiraConnectionData);
    }
}
