package io.reflectoring.jiraalerts.dashboard;

import org.apache.wicket.ajax.AjaxRequestTarget;

import io.reflectoring.jiraalerts.common.AjaxEventPayload;

/**
 * Eventpayload for Rerender {@link ApplicationStateDashboardCardPanel}.
 */
public class RerenderApplicationStateCardEventPayload extends AjaxEventPayload {

	/**
	 * Constructor.
	 *
	 * @param target
	 *            the {@link AjaxRequestTarget}.
	 */
	public RerenderApplicationStateCardEventPayload(AjaxRequestTarget target) {
		super(target);
	}
}
