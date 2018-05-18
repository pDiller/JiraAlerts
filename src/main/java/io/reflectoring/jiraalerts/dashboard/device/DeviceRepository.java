package io.reflectoring.jiraalerts.dashboard.device;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for database transactions with {@link io.reflectoring.jiraalerts.dashboard.device.Device}.
 */
@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

	@Query("SELECT d FROM Device d WHERE d.owner.id = :userId")
	List<Device> findByOwner(@Param("userId") long userId, Pageable pageable);

	@Query("SELECT count(d) FROM Device d WHERE d.owner.id = :userId")
	int countByOwner(@Param("userId") long userId);
}
