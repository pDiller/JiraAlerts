package io.reflectoring.jiraalerts.integration.admin.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstConfigurationRepository extends JpaRepository<FirstConfiguration, Long> {}
