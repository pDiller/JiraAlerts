package de.reflectoring.jiraalerts.jiracomponent.configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Mock
    private JiraConnectionDataRepository jiraConnectionDataRepositoryMock;

    @InjectMocks
    private JiraConnectionConfigurationService sut = new JiraConnectionConfigurationService();
    private JiraConnectionData jiraConnectionData;

    @Before
    public void setup() {
        jiraConnectionData = new JiraConnectionData();
        jiraConnectionData.setUrl("MyFunnyUrl.com/jira/rest");
        when(jiraConnectionDataRepositoryMock.findOne(1L)).thenReturn(jiraConnectionData);
    }

    @Test
    public void loadConnectionUrlReturnsConnectionUrlFromDataObject() {
        String loadedConnectionUrl = sut.loadConnectionUrl();

        assertThat(loadedConnectionUrl).isEqualTo("MyFunnyUrl.com/jira/rest");
    }

    @Test
    public void writeConnectionUrlUpdatesObject() throws Exception {
        sut.writeConnectionUrl("MySecondFunnyUrl.com/jira/rest");

        assertThat(jiraConnectionData.getUrl()).isNotNull();
        verify(jiraConnectionDataRepositoryMock).findOne(1L);
        verify(jiraConnectionDataRepositoryMock).save(jiraConnectionData);

    }
}
