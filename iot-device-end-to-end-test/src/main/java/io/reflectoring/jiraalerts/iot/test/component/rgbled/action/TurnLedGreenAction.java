package io.reflectoring.jiraalerts.iot.test.component.rgbled.action;

import io.reflectoring.jiraalerts.iot.component.RgbLed;
import io.reflectoring.jiraalerts.iot.test.component.Action;

public class TurnLedGreenAction extends Action {

	private final RgbLed led;

	public TurnLedGreenAction(String actionId, RgbLed led) {
		super(actionId, "Turn LED green");
		this.led = led;
	}

	@Override
	public void executeAction() {
		led.turnOff();
		led.turnGreen();
	}
}
