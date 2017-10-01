package io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FirstConfigurationService {

	@Autowired
	private FirstConfigurationRepository firstConfigurationRepository;

	public boolean isFirstConfiguration() {
		return firstConfigurationRepository.findAll().isEmpty();
	}

}
