package io.reflectoring.jiraalerts.iot.test.component.rgbled.action;

import io.reflectoring.jiraalerts.iot.component.RgbLed;
import io.reflectoring.jiraalerts.iot.test.component.Action;

public class TurnLedOnAction extends Action {

	private final RgbLed led;

	public TurnLedOnAction(String actionId, RgbLed led) {
		super(actionId, "Turn LED on");
		this.led = led;
	}

	@Override
	public void executeAction() {
		led.setAllPinsHigh();
	}
}
