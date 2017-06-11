package de.reflectoring.jiraalerts.jiracomponent.configuration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.tools.ant.taskdefs.Input;
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
     * @return Connection-URL for JIRA instance.
     */
    public String loadConnectionUrl(InputStream connectionPropertyPath) {
        try {
            Properties properties = new Properties();
            properties.load(connectionPropertyPath);
            return properties.getProperty(URL_PROPERTY);
        } catch (IOException propertyFileNotFoundException) {
            LOGGER.error("No property-file found:", propertyFileNotFoundException);
            return null;
        }
    }

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
