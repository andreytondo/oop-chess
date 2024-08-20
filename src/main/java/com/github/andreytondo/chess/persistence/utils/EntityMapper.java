package com.github.andreytondo.chess.persistence.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityMapper<T, E extends Entity<T>> {

    public void mapResultSetToEntity(ResultSet rs, E entity) throws SQLException, IllegalAccessException {
        for (Field field : entity.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                field.setAccessible(true);
                String columnName = field.getAnnotation(Column.class).name();
                Object value = rs.getObject(columnName);

                if (value != null) {
                    setValue(value, field, entity);
                }
            }
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    void setValue(Object value, Field field, E entity) throws IllegalAccessException {
        if (field.getType().isEnum()) {
            Class<? extends Enum> enumType = (Class<? extends Enum>) field.getType();
            field.set(entity, Enum.valueOf(enumType, value.toString()));
            return;
        }

        field.set(entity, value);
    }

}
