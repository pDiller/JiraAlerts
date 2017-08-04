package io.reflectoring.jiraalerts.iot.test.component.rgbled.action;

import io.reflectoring.jiraalerts.iot.component.RgbLed;
import io.reflectoring.jiraalerts.iot.test.component.Action;

public class TurnLedOffAction extends Action {

	private final RgbLed led;

	public TurnLedOffAction(String actionId, RgbLed led) {
		super(actionId, "Turn LED off");
		this.led = led;
	}

	@Override
	public void executeAction() {
		led.turnOff();
	}
}
