package io.reflectoring.jiraalerts.iot.test.component.rgbled.action;

import io.reflectoring.jiraalerts.iot.component.RgbLed;

public class TurnLedOffAction extends RgbLedAction {

	public TurnLedOffAction(String actionId, RgbLed led) {
		super(actionId, "Turn LED off", led);
	}

	@Override
	public void executeAction() {
		led.turnOff();
	}
}
