package io.reflectoring.jiraalerts.dashboard.device;

import static io.reflectoring.jiraalerts.device.DeviceType.RAITO4RPI;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import io.reflectoring.jiraalerts.device.Device;
import io.reflectoring.jiraalerts.device.DeviceRepository;
import io.reflectoring.jiraalerts.device.DeviceType;

@RunWith(MockitoJUnitRunner.class)
public class DeviceServiceTest {

	private static final long USER_ID = 1337;
	private static final int EXPECTED_COUNT = 5;
	private static final int DEVICE_ID = 4811;
	private static final String DEVICE_NAME = "TestDevice";
	private static final String DEVICE_URL = "URL";
	private static final DeviceType DEVICE_TYPE = RAITO4RPI;

	@Mock
	private DeviceRepository deviceRepositoryMock;

	@InjectMocks
	private DeviceService testSubject;

	@Test
	public void countDevicesByUserIdCallsDeviceRepository() throws Exception {
		testSubject.countDevicesByUserId(USER_ID);
		verify(deviceRepositoryMock).countByOwner(USER_ID);
	}

	@Test
	public void countDevicesByUserIdReturnsValueFromRepository() throws Exception {
		when(deviceRepositoryMock.countByOwner(USER_ID)).thenReturn(EXPECTED_COUNT);
		int loadedCount = testSubject.countDevicesByUserId(USER_ID);
		assertThat(loadedCount).isEqualTo(EXPECTED_COUNT);
	}

	@Test
	public void getDevicesByUserIdCallsDeviceRepository() throws Exception {
		PageRequest pageRequest = mock(PageRequest.class);
		testSubject.getDevicesByUserId(USER_ID, pageRequest);
		verify(deviceRepositoryMock).findByOwner(USER_ID, pageRequest);
	}

	@Test
	public void getDevicesByUserIdReturnsDevices() throws Exception {
		PageRequest pageRequest = mock(PageRequest.class);
		when(deviceRepositoryMock.findByOwner(USER_ID, pageRequest)).thenReturn(Arrays.asList(new Device(), new Device()));
		List<DeviceDTO> deviceDTOs = testSubject.getDevicesByUserId(USER_ID, pageRequest);
		assertThat(deviceDTOs).hasSize(2);
	}

	@Test
	public void getDevicesByUserIdMapsEntityValuesToDTO() throws Exception {
		Device device = new Device();
		device.setId(DEVICE_ID);
		device.setName(DEVICE_NAME);
		device.setUrl(DEVICE_URL);
		device.setType(DEVICE_TYPE);
		PageRequest pageRequest = mock(PageRequest.class);
		when(deviceRepositoryMock.findByOwner(USER_ID, pageRequest)).thenReturn(singletonList(device));

		List<DeviceDTO> deviceDTOs = testSubject.getDevicesByUserId(USER_ID, pageRequest);

		SoftAssertions softAssertions = new SoftAssertions();
		softAssertions.assertThat(deviceDTOs.get(0).getId()).isEqualTo(DEVICE_ID);
		softAssertions.assertThat(deviceDTOs.get(0).getName()).isEqualTo(DEVICE_NAME);
		softAssertions.assertThat(deviceDTOs.get(0).getUrl()).isEqualTo(DEVICE_URL);
		softAssertions.assertThat(deviceDTOs.get(0).getType()).isEqualTo(DEVICE_TYPE);
		softAssertions.assertAll();
	}

	@Test
	public void createDeviceCallsSaveOnRepository() {
		DeviceDTO deviceDTO = new DeviceDTO();
		deviceDTO.setId(DEVICE_ID);
		deviceDTO.setName(DEVICE_NAME);
		deviceDTO.setType(RAITO4RPI);
		deviceDTO.setUrl(DEVICE_URL);

		testSubject.createDevice(deviceDTO, USER_ID);

		ArgumentCaptor<Device> deviceArgumentCaptor = ArgumentCaptor.forClass(Device.class);
		verify(deviceRepositoryMock).save(deviceArgumentCaptor.capture());
		SoftAssertions softAssertions = new SoftAssertions();
		softAssertions.assertThat(deviceArgumentCaptor.getValue().getId()).isEqualTo(DEVICE_ID);
		softAssertions.assertThat(deviceArgumentCaptor.getValue().getName()).isEqualTo(DEVICE_NAME);
		softAssertions.assertThat(deviceArgumentCaptor.getValue().getType()).isEqualTo(DEVICE_TYPE);
		softAssertions.assertThat(deviceArgumentCaptor.getValue().getUrl()).isEqualTo(DEVICE_URL);
		softAssertions.assertThat(deviceArgumentCaptor.getValue().getOwner().getId()).isEqualTo(USER_ID);
		softAssertions.assertAll();
	}
}
