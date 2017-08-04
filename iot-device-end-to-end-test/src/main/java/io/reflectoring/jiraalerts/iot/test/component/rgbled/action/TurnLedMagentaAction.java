package io.reflectoring.jiraalerts.iot.test.component.rgbled.action;

import io.reflectoring.jiraalerts.iot.component.RgbLed;
import io.reflectoring.jiraalerts.iot.test.component.Action;

public class TurnLedMagentaAction extends Action {

	private final RgbLed led;

	public TurnLedMagentaAction(String actionId, RgbLed led) {
		super(actionId, "Turn LED magenta");
		this.led = led;
	}

	@Override
	public void executeAction() {
		led.turnOff();
		led.turnMagenta();
	}
}
