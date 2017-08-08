package io.reflectoring.jiraalerts.iot.test.component.steppermotor.action;

import static io.reflectoring.jiraalerts.iot.test.RaspberryPiEndToEndTest.READ_INPUT_INDICATOR;

import io.reflectoring.jiraalerts.iot.component.StepperMotor;

public class SetStepSequenceAction extends StepperMotorAction {

	private final static byte[] SINGLE_STEP_SEQUENCE = new byte[] { 0b0001, 0b0010, 0b0100, 0b1000 };
	private final static byte[] DOUBLE_STEP_SEQUNECE = new byte[] { 0b0011, 0b0110, 0b1100, 0b1001 };
	private final static byte[] HALF_STEP_SEQUNECE = new byte[] { 0b0001, 0b0011, 0b0010, 0b0110, 0b0100, 0b1100, 0b1000, 0b1001 };

	public SetStepSequenceAction(String actionId, StepperMotor stepperMotor) {
		super(actionId, "Set a specific step sequence", stepperMotor);
	}

	@Override
	public void executeAction() {
		CONSOLE.println();
		CONSOLE.println("Please select one of the following step sequences:");
		CONSOLE.println("[0] - single step");
		CONSOLE.println("[1] - double step");
		CONSOLE.println("[2] - half step");
		CONSOLE.print(READ_INPUT_INDICATOR);
		String input = System.console().readLine();

		switch (input) {
		case "0":
			stepperMotor.setStepSequence(SINGLE_STEP_SEQUENCE);
			break;
		case "1":
			stepperMotor.setStepSequence(DOUBLE_STEP_SEQUNECE);
			break;
		case "2":
			stepperMotor.setStepSequence(HALF_STEP_SEQUNECE);
			break;
		default:
			CONSOLE.println(INVALID_INPUT_MESSAGE);
		}
		CONSOLE.println();
		CONSOLE.println(RETURNING_MESSAGE);
	}
}
