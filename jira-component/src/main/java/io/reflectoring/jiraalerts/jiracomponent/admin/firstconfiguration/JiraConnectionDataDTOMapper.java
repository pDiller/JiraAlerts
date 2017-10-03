package io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration;

import org.springframework.stereotype.Component;

import io.reflectoring.jiraalerts.jiracomponent.mapper.DTOToEntityMapper;

@Component
public class JiraConnectionDataDTOMapper implements DTOToEntityMapper<JiraConnectionData, JiraConnectionDataDTO> {

	@Override
	public JiraConnectionData dtoToEntity(JiraConnectionDataDTO dto) {
		JiraConnectionData jiraConnectionData = new JiraConnectionData();
		jiraConnectionData.setId(dto.getId());
		jiraConnectionData.setModifiedAt(dto.getModifiedAt());
		jiraConnectionData.setUrl(dto.getUrl());
		jiraConnectionData.setUsername(dto.getUsername());
		return jiraConnectionData;
	}

}
