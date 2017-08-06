package io.reflectoring.jiraalerts.jiracomponent.configuration;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.reflectoring.jiraalerts.jiracomponent.connection.persistence.JiraConnectionData;
import io.reflectoring.jiraalerts.jiracomponent.connection.persistence.JiraConnectionDataRepository;

/** Service to administrate the JIRA Connection. */
@Service
@Transactional
public class JiraConnectionConfigurationService {

	@Autowired
	private JiraConnectionDataRepository jiraConnectionDataRepository;

	/**
	 * Loads the Connection for JIRA instance.
	 *
	 * @param jiraConnectionDataId
	 *            the id for the Configuration to change.
	 * @return Connection-Data for JIRA instance.
	 */
	public JiraConnectionData loadJiraConnectionData(long jiraConnectionDataId) {
		JiraConnectionData jiraConnectionData = jiraConnectionDataRepository.findOne(jiraConnectionDataId);

		checkJiraConnectionDataNotNull(jiraConnectionDataId, jiraConnectionData);

		return jiraConnectionData;
	}

	/**
	 * Writes the configured JIRA-Connection in Database.
	 *
	 * @param newJiraConnectionData
	 *            the new JIRA-Connection-Data.
	 */
	public void saveConnectionData(JiraConnectionData newJiraConnectionData) {
		newJiraConnectionData.setModifiedAt(new Date());
		jiraConnectionDataRepository.save(newJiraConnectionData);
	}

	private void checkJiraConnectionDataNotNull(long jiraConnectionDataId, JiraConnectionData jiraConnectionData) {
		if (jiraConnectionData == null) {
			throw new IllegalStateException("No configuration found for id: " + jiraConnectionDataId);
		}
	}
}
