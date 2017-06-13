package io.reflectoring.jiraalerts.iot;

import static com.pi4j.io.gpio.RaspiPin.*;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

import io.reflectoring.jiraalerts.iot.component.RgbLed;

/**
 * This class is currently used for prototype purposes. It will be removed later in development.
 */
public class RaspberryPiController {

    public static void main(String[] args) throws InterruptedException {

        GpioController gpioController = GpioFactory.getInstance();

        final RgbLed rgbLed = new RgbLed(GPIO_00, GPIO_01, GPIO_02, gpioController);

        rgbLed.setAllPinsHigh();

        Thread.sleep(2000);

        rgbLed.turnOff();

        Thread.sleep(2000);

        rgbLed.turnRed();

        Thread.sleep(2000);

        rgbLed.turnGreen();

        Thread.sleep(2000);

        rgbLed.turnBlue();

        Thread.sleep(2000);

        rgbLed.turnYellow();

        Thread.sleep(2000);

        rgbLed.turnCyan();

        Thread.sleep(2000);

        rgbLed.turnMagenta();

        Thread.sleep(2000);

        rgbLed.turnWhite();

        Thread.sleep(2000);

        rgbLed.turnOff();

        Thread.sleep(2000);

        gpioController.shutdown();
    }

}
