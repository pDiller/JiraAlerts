package io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration;

import org.springframework.stereotype.Component;

import io.reflectoring.jiraalerts.jiracomponent.mapper.DTOToEntityMapper;

@Component
public class FirstConfigurationMapper implements DTOToEntityMapper<FirstConfiguration, FirstConfigurationDTO> {

	@Override
	public FirstConfiguration dtoToEntity(FirstConfigurationDTO dto) {
		FirstConfiguration firstConfiguration = new FirstConfiguration();
		firstConfiguration.setId(dto.getId());
		firstConfiguration.setConfiguredAt(dto.getConfiguredAt());
		return firstConfiguration;
	}
}
