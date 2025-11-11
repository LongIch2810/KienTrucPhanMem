package org.example.payment_dataaccess.jsonb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

public class JsonType implements UserType {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final Class<?> returnedClass;

    public JsonType(Class<?> returnedClass) {
        this.returnedClass = returnedClass;
    }

    @Override
    public int getSqlType() {
        return Types.OTHER;
    }
    
    @Override 
    public Class<?> returnedClass() {
        return returnedClass;
    }

    @Override
    public boolean equals(Object x, Object y) {
        return Objects.equals(x, y);
    }

    @Override
    public int hashCode(Object x) {
        return Objects.hashCode(x);
    }

    @Override
    public Object nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner)
            throws SQLException {
        String value = rs.getString(position);
        if (value == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(value, returnedClass);
        } catch (JsonProcessingException e) {
            throw new HibernateException("Error deserializing JSON", e);
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
            throws SQLException {
        if (value == null) {
            st.setNull(index, Types.OTHER);
        } else {
            try {
                String json = OBJECT_MAPPER.writeValueAsString(value);
                st.setObject(index, json, Types.OTHER);
            } catch (JsonProcessingException e) {
                throw new HibernateException("Error serializing to JSON", e);
            }
        }
    }

    @Override
    public Object deepCopy(Object value) {
        if (value == null) {
            return null;
        }
        try {
            String json = OBJECT_MAPPER.writeValueAsString(value);
            return OBJECT_MAPPER.readValue(json, returnedClass);
        } catch (JsonProcessingException e) {
            throw new HibernateException("Error copying object", e);
        }
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(Object value) {
        try {
            return value == null ? null : OBJECT_MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new HibernateException("Error disassembling value", e);
        }
    }

    @Override
    public Object assemble(Serializable cached, Object owner) {
        try {
            return cached == null ? null : OBJECT_MAPPER.readValue((String) cached, returnedClass);
        } catch (JsonProcessingException e) {
            throw new HibernateException("Error assembling value", e);
        }
    }

    @Override
    public Object replace(Object original, Object target, Object owner) {
        return deepCopy(original);
    }
}