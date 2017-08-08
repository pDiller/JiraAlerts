package io.reflectoring.jiraalerts.iot.test;

import static org.apache.commons.lang3.StringUtils.trim;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.util.Console;

import io.reflectoring.jiraalerts.iot.test.component.ComponentEndToEndTest;
import io.reflectoring.jiraalerts.iot.test.component.rgbled.RgbLedEndToEndTest;
import io.reflectoring.jiraalerts.iot.test.component.steppermotor.StepperMotorEndToEndTest;

/**
 * Use this test to run against a real raspberry pi connected to different devices. You have to run this test directly on a RaspberryPi
 */
public final class RaspberryPiEndToEndTest {

	private static final List<ComponentEndToEndTest> componentEndToEndTests = new ArrayList<>();

	public static final String SEPARATOR_LINE = "###################################################################################";
	public static final String EXIT_COMMAND = "exit";
	public static final String LIST_COMMAND = "list";
	public static final String READ_INPUT_INDICATOR = "> ";

	private static Console console;
	private static GpioController gpioController;

	public static void main(String[] args) throws IOException {
		gpioController = GpioFactory.getInstance();
		console = new Console();

		createComponentList(console, gpioController);

		printTitle();

		printList();

		printEnterCommand();
		boolean exit = false;
		while (!exit) {
			console.print(READ_INPUT_INDICATOR);
			String input = System.console().readLine();
			if (EXIT_COMMAND.equalsIgnoreCase(trim(input))) {
				console.println(EXIT_COMMAND);
				exit = true;
			} else if (LIST_COMMAND.equalsIgnoreCase(trim(input))) {
				console.println(LIST_COMMAND);
				printList();
			} else {
				for (ComponentEndToEndTest componentEndToEndTest : componentEndToEndTests) {
					if (componentEndToEndTest.getComponentId().equals(trim(input))) {
						console.println(componentEndToEndTest.getComponentId());
						componentEndToEndTest.run();
						console.println("You have returned to the component-selection.");
						break;
					}
				}
			}
		}
		printExitMessage();
	}

	private static void createComponentList(Console console, GpioController gpioController) {
		componentEndToEndTests.add(new RgbLedEndToEndTest("0", "RGB Led", console, gpioController));
		componentEndToEndTests.add(new StepperMotorEndToEndTest("1", "Stepper Motor", console, gpioController));
	}

	private static void printTitle() {
		console.println();
		console.println(SEPARATOR_LINE);
		console.println("                     <-- JiraAlerts RaspberryPi Testclient -->                     ");
		console.println(" This is an end-to-end test application to test devices connect to a Raspberry Pi! ");
		console.println(SEPARATOR_LINE);
		console.println();
	}

	private static void printExitMessage() {
		console.println();
		console.println(SEPARATOR_LINE);
		console.println("                          Thanks for testing! Good-Bye!                            ");
		console.println(SEPARATOR_LINE);
		console.println();
	}

	private static void printEnterCommand() {
		console.println("Please enter the number of the component you want to test (see the list using: '" + LIST_COMMAND + "') or use '"
		        + EXIT_COMMAND + "':");
	}

	private static void printList() {
		console.println();
		console.println("Here are the known components to test:");
		for (ComponentEndToEndTest componentEndToEndTest : componentEndToEndTests) {
			console.print("[%s] - %s", componentEndToEndTest.getComponentId(), componentEndToEndTest.getComponentName());
			console.println();
		}
		console.println();
	}

}
