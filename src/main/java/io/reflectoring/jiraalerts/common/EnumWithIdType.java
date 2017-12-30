package io.reflectoring.jiraalerts.common;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

/**
 * This {@link UserType} maps a enum of type {@link EnumWithId} to, which is defined of this enum.
 *
 * @param <T>
 *            Typ of enum (has to implement {@link EnumWithId}).
 */
public abstract class EnumWithIdType<T extends EnumWithId> implements UserType {

	@Override
	public Object assemble(final Serializable cached, final Object owner) {
		return cached;
	}

	@Override
	public Object deepCopy(final Object value) {
		return value;
	}

	@Override
	public Serializable disassemble(final Object value) {
		return (Serializable) value;
	}

	@Override
	public boolean equals(final Object x, final Object y) {
		return x == y;
	}

	@Override
	public int hashCode(final Object x) {
		return x == null ? 0 : x.hashCode();
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Object nullSafeGet(final ResultSet rs, final String[] names, SessionImplementor session, Object owner) throws SQLException {
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
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws SQLException {
		if (value == null) {
			st.setNull(index, Types.INTEGER);
		} else {
			st.setInt(index, ((EnumWithId) value).getId());
		}
	}

	@Override
	public Object replace(final Object original, final Object target, final Object owner) {
		return original;
	}

	@Override
	public abstract Class<T> returnedClass();

	@Override
	public int[] sqlTypes() {
		return new int[] { Types.INTEGER };
	}

}
