package io.reflectoring.jiraalerts.iot.test.component.rgbled.action;

import io.reflectoring.jiraalerts.iot.component.RgbLed;

public class TurnLedCyanAction extends RgbLedAction {

	public TurnLedCyanAction(String actionId, RgbLed led) {
		super(actionId, "Turn LED cyan", led);
	}

	@Override
	public void executeAction() {
		led.turnOff();
		led.turnCyan();
	}
}
