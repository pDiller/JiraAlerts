package io.reflectoring.jiraalerts.iot.test.component;

import static io.reflectoring.jiraalerts.iot.test.RaspberryPiEndToEndTest.*;
import static org.apache.commons.lang3.StringUtils.trim;

import java.util.ArrayList;
import java.util.List;

import com.pi4j.util.Console;

/**
 * Abstract class for all ComponentEndToEndTest-Classes. Provides a method to run the component test.
 */
public abstract class ComponentEndToEndTest {

	protected static final String LOWER_PIN_LAYOUT = "#  1   3   5   7   9  11  13  15  17  19  21  23  25  27  29  31  33  35  37  39  #";
	protected static final String UPPER_PIN_LAYOUT = "#  2   4   6   8  10  12  14  16  18  20  22  24  26  28  30  32  34  36  38  40  #";
	protected final List<Action> actions = new ArrayList<>();
	private final String componentId;
	private final String componentName;
	protected final Console console;

	protected ComponentEndToEndTest(String componentId, String componentName, Console console) {
		this.componentId = componentId;
		this.componentName = componentName;
		this.console = console;
	}

	protected abstract void fillActions();

	public final String getComponentId() {
		return componentId;
	}

	public final String getComponentName() {
		return componentName;
	}

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
				onExit();
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

	protected abstract void printConnectionInformation();

	protected void printList() {
		console.println();
		console.println("Here are the known actions to test:");
		for (Action action : actions) {
			console.print("[%s] - %s\n", action.getActionId(), action.getActionName());
		}
		console.println();
	}

	protected void printEnterCommand() {
		console.println("Please enter the number of the action you want to test (see the list using: '" + LIST_COMMAND + "') or use '" + EXIT_COMMAND
		        + "' to return to the component-selection:");
	}

	protected abstract void onExit();
}
