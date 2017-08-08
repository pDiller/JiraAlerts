package io.reflectoring.jiraalerts.iot.test.component.rgbled;

import static com.pi4j.io.gpio.RaspiPin.*;
import static io.reflectoring.jiraalerts.iot.test.RaspberryPiEndToEndTest.SEPARATOR_LINE;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.util.Console;

import io.reflectoring.jiraalerts.iot.component.RgbLed;
import io.reflectoring.jiraalerts.iot.test.component.ComponentEndToEndTest;
import io.reflectoring.jiraalerts.iot.test.component.rgbled.action.*;

public final class RgbLedEndToEndTest extends ComponentEndToEndTest {

	private final RgbLed sut;

	public RgbLedEndToEndTest(String componentId, String componentName, Console console, GpioController gpioController) {
		super(componentId, componentName, console);
		sut = new RgbLed(GPIO_00, GPIO_01, GPIO_02, gpioController);
		fillActions();
	}

	@Override
	protected void fillActions() {
		actions.add(new TurnLedOffAction("0", sut));
		actions.add(new TurnLedOnAction("1", sut));
		actions.add(new TurnLedRedAction("2", sut));
		actions.add(new TurnLedGreenAction("3", sut));
		actions.add(new TurnLedBlueAction("4", sut));
		actions.add(new TurnLedYellowAction("5", sut));
		actions.add(new TurnLedMagentaAction("6", sut));
		actions.add(new TurnLedCyanAction("7", sut));
		actions.add(new TurnLedWhiteAction("8", sut));
	}

	protected void printConnectionInformation() {
		console.println();
		console.println("Please ensure that the RGB Led to test is connected as followed:");
		console.println(SEPARATOR_LINE);
		console.println(UPPER_PIN_LAYOUT);
		console.println("#  -   -  GND  -   -  Gre  -   -   -   -   -   -   -   -   -   -   -   -   -   -  #");
		console.println("#  -   -   -   -   -  Red Blu  -   -   -   -   -   -   -   -   -   -   -   -   -  #");
		console.println(LOWER_PIN_LAYOUT);
		console.println(SEPARATOR_LINE);
	}

	@Override
	protected void onExit() {
		sut.turnOff();
	}
}
