package io.reflectoring.jiraalerts.common;

/**
 * Interface to let enums hold an ID. Should be used, when enum-constants are stored in entities.
 */
public interface EnumWithId {

    /**
     * @return should return the ID of enum-constant
     */
    int getId();
}
