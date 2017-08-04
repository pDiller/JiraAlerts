package io.reflectoring.jiraalerts.iot.test.component.rgbled.action;

import io.reflectoring.jiraalerts.iot.component.RgbLed;
import io.reflectoring.jiraalerts.iot.test.component.Action;

public class TurnLedBlueAction extends Action {

	private final RgbLed led;

	public TurnLedBlueAction(String actionId, RgbLed led) {
		super(actionId, "Turn LED blue");
		this.led = led;
	}

	@Override
	public void executeAction() {
		led.turnOff();
		led.turnBlue();
	}
}
