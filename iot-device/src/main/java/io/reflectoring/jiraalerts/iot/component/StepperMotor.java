package io.reflectoring.jiraalerts.iot.component;

import static com.pi4j.io.gpio.PinState.LOW;

import com.pi4j.component.motor.impl.GpioStepperMotorComponent;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;

/**
 * Represents an actual Stepper Motor.
 */
public class StepperMotor {

	// anything lower than 2 ms seem not to work when using a single step sequence
	private static final int DEFAULT_STEP_INTERVAL = 2;

	// Using a 28BYJ-48 there are 32*64 (64 because of the different gears rotating) steps per revolution
	private static final int DEFAULT_STEPS_PER_REVOLUTION = 2038;

	private final GpioStepperMotorComponent motor;

	/**
	 * Contructs an instance using a default step interval as well as a default steps per revolution configuration.
	 *
	 * @param pin0
	 *            the first pin the motor is connected to
	 * @param pin1
	 *            the first pin the motor is connected to
	 * @param pin2
	 *            the first pin the motor is connected to
	 * @param pin3
	 *            the first pin the motor is connected to
	 * @param gpioController
	 *            used to provide pi4j Pin-Implementation
	 */
	public StepperMotor(Pin pin0, Pin pin1, Pin pin2, Pin pin3, GpioController gpioController) {
		this(pin0, pin1, pin2, pin3, DEFAULT_STEP_INTERVAL, DEFAULT_STEPS_PER_REVOLUTION, gpioController);
	}

	/**
	 * Contructs an instance using the given step interval as well as the given steps per revolution configuration.
	 *
	 * @param pin0
	 *            the first pin the motor is connected to
	 * @param pin1
	 *            the first pin the motor is connected to
	 * @param pin2
	 *            the first pin the motor is connected to
	 * @param pin3
	 *            the first pin the motor is connected to
	 * @param stepInterval
	 *            time in ms to wait before next step should be executed
	 * @param stepsPerRevolution
	 *            number of steps it takes the motor to perform a whole revolution (360 degrees)
	 * @param gpioController
	 *            used to provide pi4j Pin-Implementation
	 */
	public StepperMotor(Pin pin0, Pin pin1, Pin pin2, Pin pin3, int stepInterval, int stepsPerRevolution, GpioController gpioController) {
		final GpioPinDigitalOutput[] pins = new GpioPinDigitalOutput[4];
		pins[0] = gpioController.provisionDigitalOutputPin(pin0, LOW);
		pins[1] = gpioController.provisionDigitalOutputPin(pin1, LOW);
		pins[2] = gpioController.provisionDigitalOutputPin(pin2, LOW);
		pins[3] = gpioController.provisionDigitalOutputPin(pin3, LOW);

		// stop motor when the program terminates
		gpioController.setShutdownOptions(true, LOW, pins);

		motor = new GpioStepperMotorComponent(pins);
		motor.setStepInterval(stepInterval);
		motor.setStepsPerRevolution(stepsPerRevolution);
	}

	/**
	 * Sets the step sequence to use. There are different kinds of step sequences like single step, half step or double step squence.
	 */
	public void setStepSequence(byte[] sequence) {
		motor.setStepSequence(sequence);
	}

	/**
	 * Returns the step sequence currently used.
	 */
	public byte[] getStepSequence() {
		return motor.getStepSequence();
	}

	/**
	 * Rotates the motor for the given number of revolutions. You can use the value 2 to rotate the motor twice, 0.5 to rotate only half way or
	 * negative numbers like -1 to rotate once in reverse.
	 * 
	 * @param revolutions
	 *            the number of revolutions to rotate
	 */
	public void rotate(double revolutions) {
		motor.rotate(revolutions);
	}

	/**
	 * Stops the motor at the current position.
	 */
	public void stop() {
		motor.stop();
	}
}
