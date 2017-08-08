package io.reflectoring.jiraalerts.iot.test.component.rgbled.action;

import io.reflectoring.jiraalerts.iot.component.RgbLed;

public class TurnLedBlueAction extends RgbLedAction {

	public TurnLedBlueAction(String actionId, RgbLed led) {
		super(actionId, "Turn LED blue", led);
	}

	@Override
	public void executeAction() {
		led.turnOff();
		led.turnBlue();
	}
}
