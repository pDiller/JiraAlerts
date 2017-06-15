package io.reflectoring.jiraalerts.jiracomponent.configuration;

import io.reflectoring.jiraalerts.jiracomponent.connection.persistence.JiraConnectionData;
import io.reflectoring.jiraalerts.jiracomponent.connection.persistence.JiraConnectionDataRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Service to administrate the JIRA Connection. */
@Service
public class JiraConnectionConfigurationService {

  @Autowired private JiraConnectionDataRepository jiraConnectionDataRepository;

  /**
   * Loads the Connection URL for JIRA instance.
   *
   * @param jiraConnectionDataId the id for the Configuration to change.
   * @return Connection-URL for JIRA instance.
   */
  public String loadConnectionUrl(long jiraConnectionDataId) {
    JiraConnectionData jiraConnectionData =
        jiraConnectionDataRepository.findOne(jiraConnectionDataId);

    checkJiraConnectionDataNotNull(jiraConnectionDataId, jiraConnectionData);

    return jiraConnectionData.getUrl();
  }

  /**
   * Writes the configured JIRA-URL in given propertyPath.
   *
   * @param jiraConnectionDataId the id for the Configuration to change.
   * @param newConnectionUrl the new connectionUrl for the JIRA instance.
   */
  public void saveConnectionUrl(long jiraConnectionDataId, String newConnectionUrl) {
    JiraConnectionData jiraConnectionData =
        jiraConnectionDataRepository.findOne(jiraConnectionDataId);

    checkJiraConnectionDataNotNull(jiraConnectionDataId, jiraConnectionData);

    jiraConnectionData.setUrl(newConnectionUrl);
    jiraConnectionData.setModifiedAt(new Date());
    jiraConnectionDataRepository.save(jiraConnectionData);
  }

  private void checkJiraConnectionDataNotNull(
      long jiraConnectionDataId, JiraConnectionData jiraConnectionData) {
    if (jiraConnectionData == null) {
      throw new IllegalStateException("No configuration found for id: " + jiraConnectionDataId);
    }
  }
}
