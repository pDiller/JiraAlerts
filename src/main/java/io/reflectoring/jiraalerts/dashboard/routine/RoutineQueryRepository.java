package io.reflectoring.jiraalerts.dashboard.routine;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for database transactions with {@link RoutineQuery}.
 */
@Repository
public interface RoutineQueryRepository extends JpaRepository<RoutineQuery, Long> {

	@Query("SELECT rq FROM RoutineQuery rq WHERE rq.routineQueryState = 0")
	List<RoutineQuery> findAllActive();

	@Query("SELECT rq FROM RoutineQuery rq WHERE rq.owner.id = :userId")
	List<RoutineQuery> findByOwner(@Param("userId") long userId, Pageable pageable);

	@Query("SELECT count(rq) FROM RoutineQuery rq WHERE rq.owner.id = :userId")
	int countByOwner(@Param("userId") long userId);
}
