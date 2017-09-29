package io.reflectoring.jiraalerts.integration.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.reflectoring.jiraalerts.integration.admin.persistence.FirstConfiguration;
import io.reflectoring.jiraalerts.integration.admin.persistence.FirstConfigurationRepository;

@Transactional
@Service
public class FirstConfigurationService {

	@Autowired
	private FirstConfigurationRepository firstConfigurationRepository;

	public boolean isFirstConfiguration() {
		return firstConfigurationRepository.findAll().isEmpty();
	}

	public void storeFirstConfiguration(FirstConfiguration firstConfiguration) {
		firstConfigurationRepository.save(firstConfiguration);
	}
}
