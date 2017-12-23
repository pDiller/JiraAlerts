package io.reflectoring.jiraalerts.dashboard;

import org.apache.wicket.ajax.AjaxRequestTarget;

import io.reflectoring.jiraalerts.common.AjaxEventPayload;

/**
 * Eventpayload for Rerender {@link io.reflectoring.jiraalerts.dashboard.ApplicationStatusDashboardCardPanel}.
 */
public class RerenderApplicationStatusCardEventPayload extends AjaxEventPayload {

	/**
	 * Constructor.
	 *
	 * @param target
	 *            the {@link AjaxRequestTarget}.
	 */
	public RerenderApplicationStatusCardEventPayload(AjaxRequestTarget target) {
		super(target);
	}
}
