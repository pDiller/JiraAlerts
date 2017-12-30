package io.reflectoring.jiraalerts.dashboard.routine;

import io.reflectoring.jiraalerts.common.EnumWithIdType;

/**
 * Type for mapping {@link RoutineQueryState} to expected {@link org.hibernate.usertype.UserType}.
 */
public class RoutineQueryStateType extends EnumWithIdType<RoutineQueryState> {

    @Override
    public Class<RoutineQueryState> returnedClass() {
        return RoutineQueryState.class;
    }
}
