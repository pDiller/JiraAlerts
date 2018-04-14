package io.reflectoring.jiraalerts.dashboard.routine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.engine.spi.SharedSessionContractImplementor;

import io.reflectoring.jiraalerts.common.EnumWithId;
import io.reflectoring.jiraalerts.common.EnumWithIdType;

/**
 * Type for mapping {@link RoutineQueryState} to expected {@link org.hibernate.usertype.UserType}.
 */
public class RoutineQueryStateType extends EnumWithIdType {

	@Override
	public Class<RoutineQueryState> returnedClass() {
		return RoutineQueryState.class;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws SQLException {
		final int id = rs.getInt(names[0]);
		if (rs.wasNull()) {
			return null;
		}
		for (final EnumWithId value : returnedClass().getEnumConstants()) {
			if (id == value.getId()) {
				return value;
			}
		}
		throw new IllegalStateException("Unknown " + returnedClass().getSimpleName() + " id:" + id);
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws SQLException {
		if (value == null) {
			st.setNull(index, Types.INTEGER);
		} else {
			st.setInt(index, ((EnumWithId) value).getId());
		}
	}
}
