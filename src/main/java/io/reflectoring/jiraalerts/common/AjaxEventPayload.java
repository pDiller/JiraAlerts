package io.reflectoring.jiraalerts.common;

import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * Payload of {@link org.apache.wicket.event.IEvent} with a {@link AjaxRequestTarget}.
 */
public abstract class AjaxEventPayload {

	private AjaxRequestTarget target;

	public AjaxEventPayload(AjaxRequestTarget target) {
		this.target = target;
	}

	public AjaxRequestTarget getTarget() {
		return target;
	}
}
