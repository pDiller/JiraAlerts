package io.reflectoring.jiraalerts.jiraconnection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the {@link JiraConnection}.
 */
@Repository
public interface JiraConnectionRepository extends JpaRepository<JiraConnection, Long> {

}
