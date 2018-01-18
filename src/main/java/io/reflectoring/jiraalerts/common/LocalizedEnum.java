package io.reflectoring.jiraalerts.common;

/**
 * Interface to let enums have a toLocalizedString() method. Should be used, when enum values are presented on the UI.
 */
public interface LocalizedEnum {

	String toLocalizedString();
}
