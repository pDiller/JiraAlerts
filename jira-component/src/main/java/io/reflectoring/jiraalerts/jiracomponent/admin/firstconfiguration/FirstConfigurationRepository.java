package io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstConfigurationRepository extends JpaRepository<FirstConfiguration, Long> {}
