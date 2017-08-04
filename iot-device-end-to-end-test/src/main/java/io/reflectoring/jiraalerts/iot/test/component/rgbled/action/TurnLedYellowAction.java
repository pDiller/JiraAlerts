package io.reflectoring.jiraalerts.iot.test.component.rgbled.action;

import io.reflectoring.jiraalerts.iot.component.RgbLed;
import io.reflectoring.jiraalerts.iot.test.component.Action;

public class TurnLedYellowAction extends Action {

	private final RgbLed led;

	public TurnLedYellowAction(String actionId, RgbLed led) {
		super(actionId, "Turn LED yellow");
		this.led = led;
	}

	@Override
	public void executeAction() {
		led.turnOff();
		led.turnYellow();
	}
}
