package io.reflectoring.jiraalerts.iot.test.component.rgbled.action;

import io.reflectoring.jiraalerts.iot.component.RgbLed;

public class TurnLedMagentaAction extends RgbLedAction {

	public TurnLedMagentaAction(String actionId, RgbLed led) {
		super(actionId, "Turn LED magenta", led);
	}

	@Override
	public void executeAction() {
		led.turnOff();
		led.turnMagenta();
	}
}
