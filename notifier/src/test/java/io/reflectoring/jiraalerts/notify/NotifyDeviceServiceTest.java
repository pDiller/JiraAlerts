package io.reflectoring.jiraalerts.notify;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.reflectoring.jiraalerts.device.Device;
import io.reflectoring.jiraalerts.device.DeviceRepository;
import io.reflectoring.jiraalerts.iotaction.IoTActionRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class NotifyDeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepositoryMock;

    @Mock
    private IoTActionRepository ioTActionRepositoryMock;

    @InjectMocks
    private NotifyDeviceService testSubject;

    @Test
    public void notifyDevicesCallsDeviceRepositoryToLoadAllDevices() throws Exception {
        testSubject.notifyDevices();

        verify(deviceRepositoryMock).findAll();
    }

    @Test
    public void notifyDevicesLoadsIoTActionsForEachDevice() throws Exception {
        List<Device> devices = new ArrayList<>();
        Device deviceOne = new Device();
        deviceOne.setId(1);
        devices.add(deviceOne);
        Device deviceTwo = new Device();
        deviceTwo.setId(2);
        devices.add(deviceTwo);

        when(deviceRepositoryMock.findAll()).thenReturn(devices);

        testSubject.notifyDevices();

        verify(ioTActionRepositoryMock).findByDeviceOrderByPriorityFetchRoutineQuery(1);
        verify(ioTActionRepositoryMock).findByDeviceOrderByPriorityFetchRoutineQuery(2);
    }
}