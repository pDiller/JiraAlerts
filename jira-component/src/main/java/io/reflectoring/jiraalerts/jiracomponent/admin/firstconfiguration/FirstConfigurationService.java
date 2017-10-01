package io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FirstConfigurationService {

	@Autowired
	private FirstConfigurationRepository firstConfigurationRepository;

	@Autowired
	private FirstConfigurationMapper firstConfigurationMapper;

	public boolean isFirstConfiguration() {
		return firstConfigurationRepository.findAll().isEmpty();
	}

	public FirstConfiguration saveFirstConfiguration(FirstConfigurationDTO firstConfigurationDTO) {
		FirstConfiguration firstConfiguration = firstConfigurationMapper.dtoToEntity(firstConfigurationDTO);
		firstConfiguration.setConfiguredAt(new Date());
		return firstConfigurationRepository.save(firstConfiguration);
	}
}
