package io.reflectoring.jiraalerts.iot.component;

import static com.pi4j.io.gpio.PinState.LOW;
import static com.pi4j.io.gpio.RaspiPin.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class StepperMotorTest {

    private StepperMotor sut;

    @Before
    public void setUp() {
        // initiate mocks
        GpioController gpioControllerMock = mock(GpioController.class);
        when(gpioControllerMock.provisionDigitalOutputPin(GPIO_00, LOW)).thenReturn(mock(GpioPinDigitalOutput.class));
        when(gpioControllerMock.provisionDigitalOutputPin(GPIO_01, LOW)).thenReturn(mock(GpioPinDigitalOutput.class));
        when(gpioControllerMock.provisionDigitalOutputPin(GPIO_02, LOW)).thenReturn(mock(GpioPinDigitalOutput.class));
        when(gpioControllerMock.provisionDigitalOutputPin(GPIO_03, LOW)).thenReturn(mock(GpioPinDigitalOutput.class));

        // initiate testSubject
        sut = new StepperMotor(GPIO_00, GPIO_01, GPIO_02, GPIO_03, gpioControllerMock);
    }

    @Test
    public void getStepSequenceIsNullWhenNotSet() {
        // when
        byte[] stepSequence = sut.getStepSequence();

        // then
        assertThat(stepSequence).isNull();
    }

    @Test
    public void getStepSequenceReturnsSameSequenceAsSet() {
        // given
        byte[] stepSequence = new byte[] {0b0011, 0b0110, 0b1100, 0b1001};
        sut.setStepSequence(stepSequence);

        // when
        byte[] result = sut.getStepSequence();

        // then
        assertThat(result).isEqualTo(stepSequence);
    }
}
