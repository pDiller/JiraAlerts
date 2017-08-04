package io.reflectoring.jiraalerts.iot.test.component.rgbled;

import static com.pi4j.io.gpio.RaspiPin.*;
import static io.reflectoring.jiraalerts.iot.test.RaspberryPiEndToEndTest.*;
import static org.apache.commons.lang3.StringUtils.trim;

import java.util.ArrayList;
import java.util.List;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.util.Console;

import io.reflectoring.jiraalerts.iot.component.RgbLed;
import io.reflectoring.jiraalerts.iot.test.component.Action;
import io.reflectoring.jiraalerts.iot.test.component.ComponentEndToEndTest;
import io.reflectoring.jiraalerts.iot.test.component.rgbled.action.*;

public final class RgbLedEndToEndTest extends ComponentEndToEndTest {

	private static final String LOWER_PIN_LAYOUT = "#  1   3   5   7   9  11  13  15  17  19  21  23  25  27  29  31  33  35  37  39  #";
	private static final String UPPER_PIN_LAYOUT = "#  2   4   6   8  10  12  14  16  18  20  22  24  26  28  30  32  34  36  38  40  #";
	private final List<Action> actions = new ArrayList<>();
	private final RgbLed sut;

	public RgbLedEndToEndTest(String componentId, String componentName, Console console, GpioController gpioController) throws NoSuchMethodException {
		super(componentId, componentName, console);
		sut = new RgbLed(GPIO_00, GPIO_01, GPIO_02, gpioController);
		fillActions();
	}

	private void fillActions() throws NoSuchMethodException {
		actions.add(new TurnLedOnAction("0", sut));
		actions.add(new TurnLedOffAction("1", sut));
		actions.add(new TurnLedRedAction("2", sut));
		actions.add(new TurnLedGreenAction("3", sut));
		actions.add(new TurnLedBlueAction("4", sut));
		actions.add(new TurnLedYellowAction("5", sut));
		actions.add(new TurnLedMagentaAction("6", sut));
		actions.add(new TurnLedCyanAction("7", sut));
		actions.add(new TurnLedWhiteAction("8", sut));
	}

	@Override
	public void run() {
		printConnectionInformation();

		boolean exit = false;
		printList();
		printEnterCommand();

		while (!exit) {
			console.print(READ_INPUT_INDICATOR);
			String input = System.console().readLine();
			if (EXIT_COMMAND.equalsIgnoreCase(trim(input))) {
				console.println(EXIT_COMMAND);
				sut.turnOff();
				exit = true;
			} else if (LIST_COMMAND.equalsIgnoreCase(trim(input))) {
				console.println(LIST_COMMAND);
				printList();
			} else {
				try {
					int id = Integer.parseInt(input);
					try {
						Action action = actions.get(id);
						console.println(action.getActionName());
						action.executeAction();
					} catch (IndexOutOfBoundsException e) {
						console.println("There is no action with the id: " + id);
					}
				} catch (NumberFormatException e) {
					console.println("You have entered an invalid value. Please try again.");
				}
			}
		}
	}

	private void printConnectionInformation() {
		console.println();
		console.println("Please ensure that the RGB Led to test is connected as followed:");
		console.println("###################################################################################");
		console.println(UPPER_PIN_LAYOUT);
		console.println("#  -   -  GND  -   -  Gre  -   -   -   -   -   -   -   -   -   -   -   -   -   -  #");
		console.println("#  -   -   -   -   -  Red Blu  -   -   -   -   -   -   -   -   -   -   -   -   -  #");
		console.println(LOWER_PIN_LAYOUT);
		console.println("###################################################################################");
	}

	private void printEnterCommand() {
		console.println("Please enter the number of the action you want to test (see the list using: '" + LIST_COMMAND + "') or use '" + EXIT_COMMAND
		        + "' to return to the component-selection:");
	}

	private void printList() {
		console.println();
		console.println("Here are the known components to test:");
		for (Action action : actions) {
			console.print("[%s] - %s\n", action.getActionId(), action.getActionName());
		}
		console.println();
	}
}
