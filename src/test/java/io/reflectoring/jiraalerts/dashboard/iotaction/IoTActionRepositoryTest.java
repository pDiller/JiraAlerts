package io.reflectoring.jiraalerts.dashboard.iotaction;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import io.reflectoring.jiraalerts.application.testsetup.AbstractDbUnitTest;

@DatabaseSetup("IoTActionRepositoryTest.xml")
public class IoTActionRepositoryTest extends AbstractDbUnitTest {

	private static final long ROUTINE_QUERY_ID_1 = 1;
	private static final long ROUTINE_QUERY_ID_2 = 2;
	private static final long DEVICE_ID_1 = 1;
	private static final long DEVICE_ID_2 = 2;

	@Autowired
	private IoTActionRepository testSubject;

	@Test
	public void findByRoutineQueryFindsTwoIoTActionsForRoutine1() {
		List<IoTAction> iotActions = testSubject.findByRoutineQuery(ROUTINE_QUERY_ID_1, new PageRequest(0, 10, Sort.DEFAULT_DIRECTION, "device.id"));
		assertThat(iotActions).hasSize(2);
	}

	@Test
	public void findByRoutineQueryFindsOneIoTActionsForRoutine2() {
		List<IoTAction> iotActions = testSubject.findByRoutineQuery(ROUTINE_QUERY_ID_2, new PageRequest(0, 10, Sort.DEFAULT_DIRECTION, "device.id"));
		assertThat(iotActions).hasSize(1);
	}

	@Test
	public void findByDeviceFindsThreeIoTActionsForDevice1() {
		List<IoTAction> iotActions = testSubject.findByDevice(DEVICE_ID_1, new PageRequest(0, 10, Sort.DEFAULT_DIRECTION, "routineQuery.id"));
		assertThat(iotActions).hasSize(3);
	}

	@Test
	public void findByRoutineQueryFindsTwoIoTActionsForDevice2() {
		List<IoTAction> iotActions = testSubject.findByDevice(DEVICE_ID_2, new PageRequest(0, 10, Sort.DEFAULT_DIRECTION, "routineQuery.id"));
		assertThat(iotActions).hasSize(2);
	}

}
