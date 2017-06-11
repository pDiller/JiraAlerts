package de.reflectoring.jiraalerts.jiracomponent.configuration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service to administrate the JIRA Connection.
 */
@Service
public class JiraConnectionConfigurationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JiraConnectionConfigurationService.class);
    private static final String URL_PROPERTY = "url";

    /**
     * Loads the Connection URL for JIRA instance.
     * 
     * @param connectionInputStream
     *            InputStream for the propertyfile with JIRA-Url.
     * @return Connection-URL for JIRA instance.
     */
    public String loadConnectionUrl(InputStream connectionInputStream) {
        try {
            Properties properties = new Properties();
            properties.load(connectionInputStream);
            return properties.getProperty(URL_PROPERTY);
        } catch (IOException propertyFileNotFoundException) {
            LOGGER.error("No property-file found:", propertyFileNotFoundException);
            return null;
        }
    }

    /**
     * Writes the configured JIRA-URL in given propertyPath.
     *
     * @param connectionPropertyPath
     *            the path to Propertyfile for JIRA-Configuration.
     * @param connectionUrl
     *            the new connectionurl value.
     */
    public void writeConnectionUrl(String connectionPropertyPath, String connectionUrl) {
        try (OutputStream output = new FileOutputStream(connectionPropertyPath)) {
            Properties prop = new Properties();

            prop.setProperty(URL_PROPERTY, connectionUrl);

            prop.store(output, null);

        } catch (IOException propertyFileNotFoundException) {
            LOGGER.error("No property-file found:", propertyFileNotFoundException);
            throw new IllegalStateException("No property-file found:", propertyFileNotFoundException);
        }
    }
}
