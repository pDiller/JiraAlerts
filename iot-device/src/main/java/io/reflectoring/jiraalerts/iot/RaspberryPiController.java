package io.reflectoring.jiraalerts.iot;

import static com.pi4j.io.gpio.RaspiPin.*;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

import io.reflectoring.jiraalerts.iot.component.RgbLed;

/** This class is currently used for prototype purposes. It will be removed later in development. */
public class RaspberryPiController {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Hello, from the RaspberryPi!");

		GpioController gpioController = GpioFactory.getInstance();

		System.out.println("Setting up the RGB-LED");

		final RgbLed rgbLed = new RgbLed(GPIO_00, GPIO_01, GPIO_02, gpioController);

		System.out.println("Turn on");

		rgbLed.setAllPinsHigh();

		Thread.sleep(2000);

		System.out.println("Turn off");

		rgbLed.turnOff();

		Thread.sleep(2000);

		System.out.println("Turn red");

		rgbLed.turnRed();

		Thread.sleep(2000);

		System.out.println("Turn green");

		rgbLed.turnGreen();

		Thread.sleep(2000);

		System.out.println("Turn blue");

		rgbLed.turnBlue();

		Thread.sleep(2000);

		System.out.println("Turn yellow");

		rgbLed.turnYellow();

		Thread.sleep(2000);

		System.out.println("Turn cyan");

		rgbLed.turnCyan();

		Thread.sleep(2000);

		System.out.println("Turn magenta");

		rgbLed.turnMagenta();

		Thread.sleep(2000);

		System.out.println("Turn white");

		rgbLed.turnWhite();

		Thread.sleep(2000);

		System.out.println("Turn off");

		rgbLed.turnOff();

		Thread.sleep(2000);

		System.out.println("Bye bye");

		gpioController.shutdown();
	}
}
