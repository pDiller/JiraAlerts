package io.reflectoring.jiraalerts.iot;

import static com.pi4j.io.gpio.RaspiPin.*;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

import io.reflectoring.jiraalerts.iot.component.RgbLed;
import io.reflectoring.jiraalerts.iot.component.StepperMotor;

/**
 * This class is currently used for prototype purposes. It will be removed later in development.
 */
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

        System.out.println("Now some motor action!");

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

        gpioController.shutdown();
    }

}
