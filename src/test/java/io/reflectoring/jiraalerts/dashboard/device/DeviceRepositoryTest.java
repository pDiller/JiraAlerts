package io.reflectoring.jiraalerts.dashboard.device;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import io.reflectoring.jiraalerts.application.testsetup.AbstractDbUnitTest;

@DatabaseSetup("DeviceRepositoryTest.xml")
public class DeviceRepositoryTest extends AbstractDbUnitTest {

	private static final long USER_ID_1 = 1;
	private static final long USER_ID_2 = 2;

	@Autowired
	private DeviceRepository testSubject;

	@Test
	public void findByOwnerFindsFourDevicesForUserOne() throws Exception {
		List<Device> devices = testSubject.findByOwner(USER_ID_1, PageRequest.of(0, 10, Sort.DEFAULT_DIRECTION, "id"));
		assertThat(devices).hasSize(4);
	}

	@Test
	public void findByOwnerFindsTwoDevicesForUserTwo() throws Exception {
		List<Device> devices = testSubject.findByOwner(USER_ID_2, PageRequest.of(0, 10, Sort.DEFAULT_DIRECTION, "id"));
		assertThat(devices).hasSize(2);
	}

	@Test
	public void countByOwnerReturnsFourForUserOne() throws Exception {
		assertThat(testSubject.countByOwner(USER_ID_1)).isEqualTo(4);
	}

	@Test
	public void countByOwnerReturnsTwoForUserTwo() throws Exception {
		assertThat(testSubject.countByOwner(USER_ID_2)).isEqualTo(2);
	}

}
