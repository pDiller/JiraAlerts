package io.reflectoring.jiraalerts.iot.test.component;

import com.pi4j.util.Console;

/**
 * Abstract class for all ComponentEndToEndTest-Classes. Provides a method to run the component test.
 */
public abstract class ComponentEndToEndTest {

	private final String componentId;
	private final String componentName;
	protected final Console console;

	protected ComponentEndToEndTest(String componentId, String componentName, Console console) {
		this.componentId = componentId;
		this.componentName = componentName;
		this.console = console;
	}

	public final String getComponentId() {
		return componentId;
	}

	public final String getComponentName() {
		return componentName;
	}

	public abstract void run();
}
