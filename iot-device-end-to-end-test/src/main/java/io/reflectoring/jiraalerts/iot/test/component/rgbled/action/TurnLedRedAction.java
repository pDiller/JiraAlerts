package io.reflectoring.jiraalerts.iot.test.component.rgbled.action;

import io.reflectoring.jiraalerts.iot.component.RgbLed;

public class TurnLedRedAction extends RgbLedAction {

	public TurnLedRedAction(String actionId, RgbLed led) {
		super(actionId, "Turn LED red", led);
	}

	@Override
	public void executeAction() {
		led.turnOff();
		led.turnRed();
	}
}
