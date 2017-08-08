package io.reflectoring.jiraalerts.iot.test.component;

/**
 * This class needs to be extended to perfom a specific action on a component, i.e. turning a LED on or off.
 */
public abstract class Action {

	private final String actionId;
	private final String actionName;

	protected Action(String actionId, String actionName) {
		this.actionId = actionId;
		this.actionName = actionName;
	}

	public String getActionId() {
		return actionId;
	}

	public String getActionName() {
		return actionName;
	}

	public abstract void executeAction();

}
