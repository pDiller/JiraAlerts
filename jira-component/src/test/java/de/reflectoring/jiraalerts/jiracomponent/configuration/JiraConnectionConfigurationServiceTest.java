package de.reflectoring.jiraalerts.jiracomponent.configuration;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class JiraConnectionConfigurationServiceTest {

    private static final String NEW_URL_VALUE = "www.myjira-host.org";

    private JiraConnectionConfigurationService sut = new JiraConnectionConfigurationService();
    private File temporaryPropertyFile;

    @Before
    public void setup() throws IOException {
        temporaryPropertyFile = File.createTempFile("test",".properties");
    }

    @Test
    public void loadUrlReturnsHardConfiguredUrlFromPropertyFile() throws Exception {
        InputStream connectionPropertyFileInputStream = getClass().getResourceAsStream("jira-configuration-test.properties");

        String connectionUrl = sut.loadConnectionUrl(connectionPropertyFileInputStream);

        assertThat(connectionUrl).isEqualTo("<insert JIRA-restendpoint here>");
    }

    @Test
    public void writesUrlInPropertyFile() throws Exception {
        sut.writeConnectionUrl(temporaryPropertyFile.getAbsolutePath(), NEW_URL_VALUE);

        Properties properties = new Properties();
        try (InputStream configurationFileStream = new FileInputStream(temporaryPropertyFile)) {
            properties.load(configurationFileStream);
        } catch (IOException propertyFileNotFoundException) {
            fail("PropertyFile not found");
        }
        assertThat(properties.getProperty("url")).isEqualTo(NEW_URL_VALUE);
    }

    @After
    public void tearDown(){
        temporaryPropertyFile.delete();
    }
}