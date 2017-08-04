package io.reflectoring.jiraalerts.iot.test.component.rgbled.action;

import io.reflectoring.jiraalerts.iot.component.RgbLed;
import io.reflectoring.jiraalerts.iot.test.component.Action;

public class TurnLedCyanAction extends Action {

	private final RgbLed led;

	public TurnLedCyanAction(String actionId, RgbLed led) {
		super(actionId, "Turn LED cyan");
		this.led = led;
	}

	@Override
	public void executeAction() {
		led.turnOff();
		led.turnCyan();
	}
}
