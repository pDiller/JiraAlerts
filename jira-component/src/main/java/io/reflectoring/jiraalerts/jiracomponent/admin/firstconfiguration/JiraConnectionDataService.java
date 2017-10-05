package io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class JiraConnectionDataService {

	private static final long JIRA_CONNECTION_DATA_ID = 0L;

	@Autowired
	private JiraConnectionDataDTOMapper jiraConnectionDataMapper;

	@Autowired
	private JiraConnectionDataRepository jiraConnectionDataRepository;

	JiraConnectionDataDTO saveJiraConnectionData(JiraConnectionDataDTO jiraConnectionDataDTO) {
		JiraConnectionData jiraConnectionData = jiraConnectionDataMapper.dtoToEntity(jiraConnectionDataDTO);
		jiraConnectionData.setModifiedAt(new Date());
		jiraConnectionData.setId(JIRA_CONNECTION_DATA_ID);
		jiraConnectionData = jiraConnectionDataRepository.save(jiraConnectionData);

		return jiraConnectionDataMapper.entityToDTO(jiraConnectionData);
	}

	public boolean isFirstConfiguration() {
		return jiraConnectionDataRepository.findAll().isEmpty();
	}

	public JiraConnectionDataDTO getJiraConnectionDataDTO(Long jiraConnectionDataID) {
		return jiraConnectionDataMapper.entityToDTO(jiraConnectionDataRepository.findOne(jiraConnectionDataID));
	}
}
