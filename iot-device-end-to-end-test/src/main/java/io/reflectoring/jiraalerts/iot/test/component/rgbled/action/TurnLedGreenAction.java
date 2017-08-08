package io.reflectoring.jiraalerts.iot.test.component.rgbled.action;

import io.reflectoring.jiraalerts.iot.component.RgbLed;

public class TurnLedGreenAction extends RgbLedAction {

	public TurnLedGreenAction(String actionId, RgbLed led) {
		super(actionId, "Turn LED green", led);
	}

	@Override
	public void executeAction() {
		led.turnOff();
		led.turnGreen();
	}
}
