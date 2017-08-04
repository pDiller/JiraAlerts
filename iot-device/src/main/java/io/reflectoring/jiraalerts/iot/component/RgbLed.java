package io.reflectoring.jiraalerts.iot.component;

import static com.pi4j.io.gpio.PinState.HIGH;
import static com.pi4j.io.gpio.PinState.LOW;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

/** Represents an actual RGB-LED. */
public class RgbLed {

	private final GpioPinDigitalOutput redPin;
	private final GpioPinDigitalOutput greenPin;
	private final GpioPinDigitalOutput bluePin;

	/**
	 * Creates an instance of an RGB-LED which is connected via the given Pins.
	 *
	 * @param redPin
	 *            the pin which connects to the red leg
	 * @param greenPin
	 *            the pin which connects to the green leg
	 * @param bluePin
	 *            the pin which connects to the blue leg
	 * @param gpioController
	 *            used to get the actual OutputPins using the given pins
	 */
	public RgbLed(Pin redPin, Pin greenPin, Pin bluePin, GpioController gpioController) {
		this.redPin = gpioController.provisionDigitalOutputPin(redPin, "red", LOW);
		this.greenPin = gpioController.provisionDigitalOutputPin(greenPin, "green", LOW);
		this.bluePin = gpioController.provisionDigitalOutputPin(bluePin, "blue", LOW);
	}

	/**
	 * Checks if any of the three pins is set to high.
	 *
	 * @return true if at least one of the three pins is set to high, else false
	 */
	boolean isOn() {
		return redPin.isHigh() || greenPin.isHigh() || bluePin.isHigh();
	}

	/** Sets all pins to high. */
	public void setAllPinsHigh() {
		setPinsTo(HIGH, HIGH, HIGH);
	}

	/** Turns the RGB-LED red. */
	public void turnRed() {
		setPinsTo(HIGH, LOW, LOW);
	}

	/** Turns the RGB-LED green. */
	public void turnGreen() {
		setPinsTo(LOW, HIGH, LOW);
	}

	/** Turns the RGB-LED blue. */
	public void turnBlue() {
		setPinsTo(LOW, LOW, HIGH);
	}

	/** Turns the RGB-LED yellow. */
	public void turnYellow() {
		setPinsTo(HIGH, HIGH, LOW);
	}

	/** Turns the RGB-LED cyan. */
	public void turnCyan() {
		setPinsTo(LOW, HIGH, HIGH);
	}

	/** Turns the RGB-LED magenta. */
	public void turnMagenta() {
		setPinsTo(HIGH, LOW, HIGH);
	}

	/** Turns the RGB-LED white. Same as {@link RgbLed#setAllPinsHigh()} */
	public void turnWhite() {
		setAllPinsHigh();
	}

	/** Turns the RGB-LED off. */
	public void turnOff() {
		setPinsTo(LOW, LOW, LOW);
	}

	/** Sets the RGB-LED to the given states. */
	private void setPinsTo(PinState redPinState, PinState greenPinState, PinState bluePinState) {
		redPin.setState(redPinState);
		greenPin.setState(greenPinState);
		bluePin.setState(bluePinState);
	}
}
