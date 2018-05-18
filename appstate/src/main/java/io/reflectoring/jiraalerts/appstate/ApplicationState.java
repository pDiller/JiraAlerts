package io.reflectoring.jiraalerts.appstate;

/**
 * The different states for the application.
 */
public enum ApplicationState {

	/**
	 * Not initialized and not active.
	 */
	NOT_INITIALIZED,

	/**
	 * Initialized but not active.
	 */
	NOT_ACTIVE,

	/**
	 * Initialized and active.
	 */
	ACTIVE;

}
