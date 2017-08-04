package io.reflectoring.jiraalerts.iot.test;

import com.pi4j.util.Console;

import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.trim;

/**
 * Use this test to run against a real raspberry pi connected to different devices. You have to run this test directly on a RaspberryPi
 */
public class RaspberryPiEndToEndTest {

	private static final String EXIT_COMMAND = "exit";
	private static final String LIST_COMMAND = "list";
	private static final String READ_INPUT_INDICATOR = "> ";

	private static Console console;

	public static void main(String[] args) throws IOException {
		console = new Console();
		printTitle();

		printList();

		boolean exit = false;
		while (!exit) {
			printEnterCommand();
			String input = System.console().readLine();
			if (EXIT_COMMAND.equalsIgnoreCase(trim(input))) {
				exit = true;
			} else if (LIST_COMMAND.equalsIgnoreCase(trim(input))) {
				printList();
			}
		}
		printExitMessage();
	}

	private static void printTitle() {
		console.println();
		console.println("###########################################################################################");
		console.println("                         <-- JiraAlerts RaspberryPi Testclient -->                         ");
		console.println("     This is an end-to-end test application to test devices connect to a Raspberry Pi!     ");
		console.println("###########################################################################################");
		console.println();
	}

	private static void printExitMessage() {
		console.println();
		console.println("###########################################################################################");
		console.println("                              Thanks for testing! Good-Bye!                                ");
		console.println("###########################################################################################");
		console.println();
	}

	private static void printEnterCommand() {
		console.println("Please enter the number of the component you want to test (see the list using: '" + LIST_COMMAND + "') or use '"
		        + EXIT_COMMAND + "':");
		console.print(READ_INPUT_INDICATOR);
	}

	private static void printList() {
		console.println("Here are the known components to test:");
		console.println("[0] Template - there is nothing here yet...");
	}

}
