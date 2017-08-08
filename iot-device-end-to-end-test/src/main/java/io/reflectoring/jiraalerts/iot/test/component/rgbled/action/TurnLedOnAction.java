package io.reflectoring.jiraalerts.iot.test.component.rgbled.action;

import io.reflectoring.jiraalerts.iot.component.RgbLed;

public class TurnLedOnAction extends RgbLedAction {

	public TurnLedOnAction(String actionId, RgbLed led) {
		super(actionId, "Turn LED on", led);
	}

	@Override
	public void executeAction() {
		led.setAllPinsHigh();
	}
}
