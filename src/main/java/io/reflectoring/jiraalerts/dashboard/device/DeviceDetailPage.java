package io.reflectoring.jiraalerts.dashboard.device;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

import io.reflectoring.jiraalerts.base.BasePage;

/**
 * Detail page for devices.
 */
@AuthorizeInstantiation("administrator")
public class DeviceDetailPage extends BasePage {}
