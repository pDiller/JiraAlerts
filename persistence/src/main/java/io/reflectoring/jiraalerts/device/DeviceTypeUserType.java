package io.reflectoring.jiraalerts.device;

import io.reflectoring.jiraalerts.common.EnumWithId;
import io.reflectoring.jiraalerts.common.EnumWithIdType;
import io.reflectoring.jiraalerts.device.DeviceType;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Type definition for mapping {@link DeviceType} to expected {@link org.hibernate.usertype.UserType}.
 */
public class DeviceTypeUserType extends EnumWithIdType {

    @Override
    public Class<DeviceType> returnedClass() {
        return DeviceType.class;
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
