package com.github.andreytondo.chess.persistence.utils;


import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlGenerator<T, E extends Entity<T>> {

    private Class<E> clazz;

    public void setClazz(Class<E> clazz) {
        this.clazz = clazz;
    }

    public String generateInsertSql() {
        String tableName = extractTableName(clazz);
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                columns.append(column.name()).append(", ");
                values.append("?, ");
            }
        }

        removeTrailingComma(columns);
        removeTrailingComma(values);

        return String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, columns, values);
    }

    public String generateSelectSql() {
        String tableName = extractTableName(clazz);
        StringBuilder columns = new StringBuilder();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                columns.append(column.name()).append(", ");
            }
        }

        removeTrailingComma(columns);
        return String.format("SELECT %s FROM %s", columns, tableName);
    }

    public void populateStatementWithValues(E entity, PreparedStatement stmt) throws SQLException, IllegalAccessException {
        int parameterIndex = 1;

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                field.setAccessible(true);
                stmt.setObject(parameterIndex++, getValueByFieldType(entity, field));
            }
        }
    }

    private String extractTableName(Class<?> clazz) {
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        if (tableAnnotation == null || tableAnnotation.name().isEmpty()) {
            throw new IllegalArgumentException("Entity must have a @Table annotation with a name.");
        }
        return String.format("%s.%s", tableAnnotation.schema(), tableAnnotation.name());
    }

    private void removeTrailingComma(StringBuilder sb) {
        if (!sb.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
    }

    private Object getValueByFieldType(E entity, Field field) throws IllegalAccessException {
        Object value = field.get(entity);

        if (value instanceof Enum<?>) {
            return value.toString();
        }

        return value;
    }


}
