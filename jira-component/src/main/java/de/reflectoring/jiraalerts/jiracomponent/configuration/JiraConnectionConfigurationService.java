package de.reflectoring.jiraalerts.jiracomponent.configuration;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.reflectoring.jiraalerts.jiracomponent.connection.persistence.JiraConnectionData;
import de.reflectoring.jiraalerts.jiracomponent.connection.persistence.JiraConnectionDataRepository;

/**
 * Service to administrate the JIRA Connection.
 */
@Service
public class JiraConnectionConfigurationService {

    @Autowired
    private JiraConnectionDataRepository jiraConnectionDataRepository;

    public JiraConnectionConfigurationService() {
        System.out.println(getClass());
    }

    /**
     * Loads the Connection URL for JIRA instance.
     * 
     * @return Connection-URL for JIRA instance.
     */
    public String loadConnectionUrl() {
        JiraConnectionData jiraConnectionData = jiraConnectionDataRepository.findOne(1L);
        return jiraConnectionData.getUrl();
    }

    /**
     * Writes the configured JIRA-URL in given propertyPath.
     * 
     * @param newConnectionUrl
     *            the new connectionUrl for the JIRA instance.
     */
    public void writeConnectionUrl(String newConnectionUrl) {
        JiraConnectionData jiraConnectionData = jiraConnectionDataRepository.findOne(1L);
        jiraConnectionData.setUrl(newConnectionUrl);
        jiraConnectionData.setModifiedAt(new Date());
        jiraConnectionDataRepository.save(jiraConnectionData);
    }
}
