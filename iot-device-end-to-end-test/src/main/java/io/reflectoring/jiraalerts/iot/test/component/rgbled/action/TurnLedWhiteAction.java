package io.reflectoring.jiraalerts.iot.test.component.rgbled.action;

import io.reflectoring.jiraalerts.iot.component.RgbLed;
import io.reflectoring.jiraalerts.iot.test.component.Action;

public class TurnLedWhiteAction extends Action {

	private final RgbLed led;

	public TurnLedWhiteAction(String actionId, RgbLed led) {
		super(actionId, "Turn LED white");
		this.led = led;
	}

	@Override
	public void executeAction() {
		led.turnWhite();
	}
}
