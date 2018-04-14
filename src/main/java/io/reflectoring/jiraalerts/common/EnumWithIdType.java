package io.reflectoring.jiraalerts.common;

import java.io.Serializable;
import java.sql.Types;

import org.hibernate.usertype.UserType;

/**
 * This {@link UserType} maps a enum of type {@link EnumWithId} to, which is defined of this enum.
 */
public abstract class EnumWithIdType implements UserType {

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
	public Object replace(final Object original, final Object target, final Object owner) {
		return original;
	}

	@Override
	public int[] sqlTypes() {
		return new int[] { Types.INTEGER };
	}

}
