package io.reflectoring.jiraalerts.application;

/**
 * The different states for the application.
 */
public enum ApplicationStatus {

	/**
	 * Not initialized and not active.
	 */
	NOT_INITIALIZED(0),

	/**
	 * Initialized but not active.
	 */
	NOT_ACTIVE(1),

	/**
	 * Initialized and active.
	 */
	ACTIVE(2);

	private int id;

	ApplicationStatus(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
