package io.reflectoring.jiraalerts.iot.test.component.rgbled.action;

import io.reflectoring.jiraalerts.iot.component.RgbLed;
import io.reflectoring.jiraalerts.iot.test.component.Action;

public class TurnLedRedAction extends Action {

	private final RgbLed led;

	public TurnLedRedAction(String actionId, RgbLed led) {
		super(actionId, "Turn LED red");
		this.led = led;
	}

	@Override
	public void executeAction() {
		led.turnOff();
		led.turnRed();
	}
}
