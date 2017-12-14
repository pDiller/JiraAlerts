package io.reflectoring.jiraalerts.admin.firstconfiguration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JiraConnectionDataRepository extends JpaRepository<JiraConnectionData, Long> {}
