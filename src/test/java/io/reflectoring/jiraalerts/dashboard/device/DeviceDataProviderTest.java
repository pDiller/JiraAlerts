package io.reflectoring.jiraalerts.dashboard.device;

import static org.assertj.core.api.Assertions.assertThat;
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

import io.reflectoring.jiraalerts.application.testsetup.TestApplication;

@RunWith(MockitoJUnitRunner.class)
public class DeviceDataProviderTest {

	private static final int ROWS_PER_PAGE = 5;
	private static final long USER_ID = 1337;

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
		long size = testSubject.size();
		// TODO
	}

	@Test
	public void iteratorReturnsValueFromService() throws Exception {
		List<DeviceDTO> deviceDTOs = Arrays.asList(new DeviceDTO(), new DeviceDTO());
		Iterator<? extends DeviceDTO> iterator = testSubject.iterator(1, 5);
		// TODO
	}

	@Test
	public void modelWrapsDTO() throws Exception {
		DeviceDTO deviceDTO = new DeviceDTO();
		IModel<DeviceDTO> model = testSubject.model(deviceDTO);
		assertThat(model.getObject()).isSameAs(deviceDTO);
	}
}
