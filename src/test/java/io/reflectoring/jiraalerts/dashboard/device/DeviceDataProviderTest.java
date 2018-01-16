package io.reflectoring.jiraalerts.dashboard.device;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import io.reflectoring.jiraalerts.application.testsetup.TestApplication;

@RunWith(MockitoJUnitRunner.class)
public class DeviceDataProviderTest {

	private static final int ROWS_PER_PAGE = 5;
	private static final long USER_ID = 1337;
	private static final int ENTRY_COUNT = 4711;

	@Mock
	private DeviceService deviceServiceMock;

	private DeviceDataProvider testSubject;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
		new WicketTester(new TestApplication(this));
		testSubject = new DeviceDataProvider(USER_ID, ROWS_PER_PAGE);
	}

	@Test
	public void sizeReturnsValueFromService() throws Exception {
		when(deviceServiceMock.countDevicesByUserId(USER_ID)).thenReturn(ENTRY_COUNT);
		long size = testSubject.size();
		assertThat(size).isEqualTo(ENTRY_COUNT);
	}

	@Test
	public void sizeLetServiceCountEntries() throws Exception {
		testSubject.size();
		verify(deviceServiceMock).countDevicesByUserId(USER_ID);
	}

	@Test
	public void iteratorReturnsValueFromService() throws Exception {
		List<DeviceDTO> deviceDTOs = Arrays.asList(new DeviceDTO(), new DeviceDTO());
		when(deviceServiceMock.getDevicesByUserId(eq(USER_ID), any(PageRequest.class))).thenReturn(deviceDTOs);
		Iterator<? extends DeviceDTO> iterator = testSubject.iterator(1, 5);
		assertThat(iterator).hasSize(2);
	}

	@Test
	public void iteratorLetServiceLoadEntries() throws Exception {
		testSubject.iterator(1, 5);
		verify(deviceServiceMock).getDevicesByUserId(eq(USER_ID), any(PageRequest.class));
	}

	@Test
	public void modelWrapsDTO() throws Exception {
		DeviceDTO deviceDTO = new DeviceDTO();
		IModel<DeviceDTO> model = testSubject.model(deviceDTO);
		assertThat(model.getObject()).isSameAs(deviceDTO);
	}
}
