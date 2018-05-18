package io.reflectoring.jiraalerts.iotaction;

import java.util.List;

import io.reflectoring.jiraalerts.device.Device;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for database transactions with {@link IoTAction}.
 */
@Repository
public interface IoTActionRepository extends JpaRepository<IoTAction, IoTAction.IoTActionId> {

	@Query("SELECT i FROM IoTAction i WHERE i.routineQuery.id = :routineQueryId")
	List<IoTAction> findByRoutineQuery(@Param("routineQueryId") long routineQueryId, Pageable pageable);

	@Query("SELECT i FROM IoTAction i WHERE i.device.id = :deviceId")
	List<IoTAction> findByDevice(@Param("deviceId") long deviceId, Pageable pageable);

	@Query("SELECT i FROM IoTAction i JOIN FETCH i.routineQuery WHERE i.device.id = :deviceId ORDER BY i.priority")
	List<IoTAction> findByDeviceOrderByPriorityFetchRoutineQuery(@Param("deviceId")long deviceId);
}
