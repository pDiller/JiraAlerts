package io.reflectoring.jiraalerts.dashboard.routine;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.reflectoring.jiraalerts.application.login.User;

/**
 * Repository for database transactions with {@link RoutineQuery}.
 */
@Repository
public interface RoutineQueryRepository extends JpaRepository<RoutineQuery, Long> {

	List<RoutineQuery> findByOwner(User user);

	int countByOwner(User user);
}
