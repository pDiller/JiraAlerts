package io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration;

import org.springframework.stereotype.Component;

import io.reflectoring.jiraalerts.jiracomponent.mapper.Mapper;

@Component
public class JiraConnectionDataMapper implements Mapper<JiraConnectionData, JiraConnectionDataDTO> {

	@Override
	public JiraConnectionData dtoToEntity(JiraConnectionDataDTO dto) {
		JiraConnectionData jiraConnectionData = new JiraConnectionData();
		jiraConnectionData.setId(dto.getId());
		jiraConnectionData.setModifiedAt(dto.getModifiedAt());
		jiraConnectionData.setUrl(dto.getUrl());
		jiraConnectionData.setUsername(dto.getUsername());
		return jiraConnectionData;
	}

	@Override
	public JiraConnectionDataDTO entityToDTO(JiraConnectionData entity) {
		JiraConnectionDataDTO jiraConnectionDataDTO = new JiraConnectionDataDTO();
		jiraConnectionDataDTO.setId(entity.getId());
		jiraConnectionDataDTO.setModifiedAt(entity.getModifiedAt());
		jiraConnectionDataDTO.setUrl(entity.getUrl());
		jiraConnectionDataDTO.setUsername(entity.getUsername());
		return jiraConnectionDataDTO;
	}
}
