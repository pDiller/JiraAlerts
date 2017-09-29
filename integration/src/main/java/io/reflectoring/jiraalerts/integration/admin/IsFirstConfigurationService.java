package io.reflectoring.jiraalerts.integration.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import io.reflectoring.jiraalerts.integration.admin.persistence.FirstConfigurationRepository;

@Transactional
public class IsFirstConfigurationService {

	private static final Long ZERO = 0L;

	@Autowired
	private FirstConfigurationRepository firstConfigurationRepository;

	public boolean isFirstConfiguration() {
		return firstConfigurationRepository.findOne(ZERO) == null;
	}
}
