package io.reflectoring.jiraalerts.dashboard.device;

import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import io.reflectoring.jiraalerts.application.login.User;

@RunWith(MockitoJUnitRunner.class)
public class DeviceServiceTest {

	private static final long USER_ID = 1337;
	private static final int EXPECTED_COUNT = 5;
	private static final User DEVICE_OWNER = new User();

	@InjectMocks
	private DeviceService testSubject;

	@Test
	public void countDevicesByUserIdCallsDeviceRepository() throws Exception {
		testSubject.countDevicesByUserId(USER_ID);
		// TODO
	}

	@Test
	public void countDevicesByUserIdReturnsValueFromRepository() throws Exception {
		int loadedCount = testSubject.countDevicesByUserId(USER_ID);
		// TODO
	}

	@Test
	public void getDevicesByUserIdCallsDeviceRepository() throws Exception {
		PageRequest pageRequest = mock(PageRequest.class);
		testSubject.getDevicesByUserId(USER_ID, pageRequest);
		// TODO
	}

	@Test
	public void getDevicesByUserIdReturnsDevices() throws Exception {
		PageRequest pageRequest = mock(PageRequest.class);
		List<DeviceDTO> deviceDTOs = testSubject.getDevicesByUserId(USER_ID, pageRequest);
		// TODO
	}
}
