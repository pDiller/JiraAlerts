package io.reflectoring.jiraalerts.iot;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import io.reflectoring.jiraalerts.iot.component.StepperMotor;

import static com.pi4j.io.gpio.RaspiPin.*;

/** This class is currently used for prototype purposes. It will be removed later in development. */
public class RaspberryPiController {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Hello, from the RaspberryPi!");

		GpioController gpioController = GpioFactory.getInstance();

		StepperMotor motor = new StepperMotor(GPIO_03, GPIO_04, GPIO_05, GPIO_06, gpioController);

		byte[] singleStepSequence = new byte[4];
		singleStepSequence[0] = (byte) 0b0011;
		singleStepSequence[1] = (byte) 0b0110;
		singleStepSequence[2] = (byte) 0b1100;
		singleStepSequence[3] = (byte) 0b1001;

		motor.setStepSequence(singleStepSequence);

		Thread.sleep(2000);

		System.out.println("Turn half way");

		motor.rotate(0.50);

		Thread.sleep(2000);

		System.out.println("Turn back");

		motor.rotate(-0.5);

		System.out.println("Good boy!");

		Thread.sleep(2000);

		System.out.println("Bye bye");

		System.out.println("Bye bye");

		gpioController.shutdown();
	}
}
