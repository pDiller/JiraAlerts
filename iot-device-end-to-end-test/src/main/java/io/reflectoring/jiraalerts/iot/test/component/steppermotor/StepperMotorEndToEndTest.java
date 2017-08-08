package io.reflectoring.jiraalerts.iot.test.component.steppermotor;

import static com.pi4j.io.gpio.RaspiPin.*;
import static io.reflectoring.jiraalerts.iot.test.RaspberryPiEndToEndTest.SEPARATOR_LINE;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.util.Console;

import io.reflectoring.jiraalerts.iot.component.StepperMotor;
import io.reflectoring.jiraalerts.iot.test.component.ComponentEndToEndTest;
import io.reflectoring.jiraalerts.iot.test.component.steppermotor.action.RotateAction;
import io.reflectoring.jiraalerts.iot.test.component.steppermotor.action.SetStepSequenceAction;

public final class StepperMotorEndToEndTest extends ComponentEndToEndTest {

	private final StepperMotor sut;

	public StepperMotorEndToEndTest(String componentId, String componentName, Console console, GpioController gpioController) {
		super(componentId, componentName, console);
		sut = new StepperMotor(GPIO_03, GPIO_04, GPIO_05, GPIO_06, gpioController);
		fillActions();
	}

	@Override
	protected void fillActions() {
		actions.add(new SetStepSequenceAction("0", sut));
		actions.add(new RotateAction("1", sut));
	}

	@Override
	protected void printConnectionInformation() {
		console.println();
		console.println("Please ensure that the StepperMotor to test is connected as followed:");
		console.println(SEPARATOR_LINE);
		console.println(UPPER_PIN_LAYOUT);
		console.println("# VCC  -  GND  -   -   -   -  P1  P2   -  P3   -   -   -   -   -   -   -   -   -  #");
		console.println("#  -   -   -   -   -   -   -  P0   -   -   -   -   -   -   -   -   -   -   -   -  #");
		console.println(LOWER_PIN_LAYOUT);
		console.println(SEPARATOR_LINE);
	}

	@Override
	protected void onExit() {
		sut.stop();
	}
}
