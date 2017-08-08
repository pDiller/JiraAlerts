package io.reflectoring.jiraalerts.iot.test.component.rgbled.action;

import io.reflectoring.jiraalerts.iot.component.RgbLed;
import io.reflectoring.jiraalerts.iot.test.component.Action;

abstract class RgbLedAction extends Action {

	final RgbLed led;

	RgbLedAction(String actionId, String actionName, RgbLed led) {
		super(actionId, actionName);
		this.led = led;
	}
}
