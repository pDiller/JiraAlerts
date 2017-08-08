package io.reflectoring.jiraalerts.iot.test.component.rgbled.action;

import io.reflectoring.jiraalerts.iot.component.RgbLed;

public class TurnLedYellowAction extends RgbLedAction {

	public TurnLedYellowAction(String actionId, RgbLed led) {
		super(actionId, "Turn LED yellow", led);
	}

	@Override
	public void executeAction() {
		led.turnOff();
		led.turnYellow();
	}
}
