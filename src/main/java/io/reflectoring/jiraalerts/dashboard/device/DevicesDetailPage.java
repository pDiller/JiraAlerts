package io.reflectoring.jiraalerts.dashboard.device;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

import io.reflectoring.jiraalerts.application.JiraAlertsSession;
import io.reflectoring.jiraalerts.base.BasePage;

/**
 * Detail page listing all devices.
 */
@AuthorizeInstantiation("administrator")
public class DevicesDetailPage extends BasePage {

	public DevicesDetailPage() {
		add(new DeviceDetailsTable("deviceTable", JiraAlertsSession.get().getUserId()));
	}
}
