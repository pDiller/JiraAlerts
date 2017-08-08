package io.reflectoring.jiraalerts.iot.test.component.rgbled.action;

import io.reflectoring.jiraalerts.iot.component.RgbLed;

public class TurnLedWhiteAction extends RgbLedAction {

	public TurnLedWhiteAction(String actionId, RgbLed led) {
		super(actionId, "Turn LED white", led);
	}

	@Override
	public void executeAction() {
		led.turnWhite();
	}
}
