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

	public JiraConnectionDataDTO saveJiraConnectionData(JiraConnectionDataDTO jiraConnectionDataDTO) {
		JiraConnectionData jiraConnectionData = jiraConnectionDataMapper.dtoToEntity(jiraConnectionDataDTO);
		jiraConnectionData.setModifiedAt(new Date());
		jiraConnectionData = jiraConnectionDataRepository.save(jiraConnectionData);

		return jiraConnectionDataMapper.entityToDTO(jiraConnectionData);
	}

	public boolean isFirstConfiguration() {
		return jiraConnectionDataRepository.findAll().isEmpty();
	}

	public JiraConnectionDataDTO getJiraConnectionDataDTO() {
		return jiraConnectionDataMapper.entityToDTO(jiraConnectionDataRepository.findAll().get(0));
	}
}
