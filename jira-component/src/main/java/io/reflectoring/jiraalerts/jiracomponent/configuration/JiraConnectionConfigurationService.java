package io.reflectoring.jiraalerts.jiracomponent.configuration;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.reflectoring.jiraalerts.jiracomponent.configuration.persistence.JiraConnectionData;
import io.reflectoring.jiraalerts.jiracomponent.configuration.persistence.JiraConnectionDataRepository;

/** Service to administrate the JIRA Connection. */
@Service
@Transactional
public class JiraConnectionConfigurationService {

	@Autowired
	private JiraConnectionDataRepository jiraConnectionDataRepository;

	@Autowired
	private JiraConnectionDataMapper jiraConnectionDataMapper;

	/**
	 * Loads the Connection for JIRA instance.
	 *
	 * @param jiraConnectionDataId
	 *            the id for the Configuration to change.
	 * @return Connection-Data for JIRA instance.
	 */
	public JiraConnectionDataDTO loadJiraConnectionData(long jiraConnectionDataId) {
		JiraConnectionData jiraConnectionData = jiraConnectionDataRepository.findOne(jiraConnectionDataId);

		if (jiraConnectionData == null) {
			return new JiraConnectionDataDTO();
		}

		return jiraConnectionDataMapper.entityToDTO(jiraConnectionData);
	}

	/**
	 * Writes the configured JIRA-Connection in Database.
	 *
	 * @param newJiraConnectionDataDTO
	 *            the new JIRA-Connection-Data.
	 */
	public void saveConnectionData(JiraConnectionDataDTO newJiraConnectionDataDTO) {
		newJiraConnectionDataDTO.setModifiedAt(new Date());
		JiraConnectionData newJiraConnectionData = jiraConnectionDataMapper.dtoToEntity(newJiraConnectionDataDTO);

		jiraConnectionDataRepository.save(newJiraConnectionData);
	}

}
