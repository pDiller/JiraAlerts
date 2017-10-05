package io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class JiraConnectionDataService {

	@Autowired
	private JiraConnectionDataDTOMapper jiraConnectionDataMapper;

	@Autowired
	private JiraConnectionDataRepository jiraConnectionDataRepository;

	public JiraConnectionData saveJiraConnectionData(JiraConnectionDataDTO jiraConnectionDataDTO) {
		JiraConnectionData jiraConnectionData = jiraConnectionDataMapper.dtoToEntity(jiraConnectionDataDTO);
		jiraConnectionData.setModifiedAt(new Date());
		return jiraConnectionDataRepository.save(jiraConnectionData);
	}

	public boolean isFirstConfiguration() {
		return jiraConnectionDataRepository.findAll().isEmpty();
	}
}
