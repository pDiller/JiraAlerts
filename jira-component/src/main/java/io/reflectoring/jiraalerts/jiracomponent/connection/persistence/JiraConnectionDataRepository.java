package io.reflectoring.jiraalerts.jiracomponent.connection.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JiraConnectionDataRepository extends JpaRepository<JiraConnectionData, Long> {}
