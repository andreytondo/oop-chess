package com.github.andreytondo.chess.persistence;

import com.github.andreytondo.chess.persistence.postgresql.PostgresDatabase;
import com.github.andreytondo.chess.persistence.utils.Entity;
import com.github.andreytondo.chess.persistence.utils.EntityMapper;
import com.github.andreytondo.chess.persistence.utils.SqlGenerator;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractDAO<T, E extends Entity<T>> {

    private static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<E> clazz;
    private final Database database = new PostgresDatabase();
    private final SqlGenerator<T, E> sqlGenerator = new SqlGenerator<>();
    private final EntityMapper<T, E> entityMapper = new EntityMapper<>();

    protected AbstractDAO(Class<E> clazz) {
        this.clazz = clazz;
        this.sqlGenerator.setClazz(clazz);
    }

    protected Database getDatabase() {
        return database;
    }

    public void create(E entity) {
        Objects.requireNonNull(entity, "Entity cannot be null");
        String sql = sqlGenerator.generateInsertSql();

        try (Connection connection = getDatabase().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            sqlGenerator.populateStatementWithValues(entity, stmt);
            stmt.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.SEVERE, "Failed to create object: " + clazz.getSimpleName(), e);
            throw new RuntimeException("Failed to create object", e);
        }
    }

    public List<E> getAll() {
        List<E> entities = new ArrayList<>();
        String sql = sqlGenerator.generateSelectSql();

        try (Connection connection = getDatabase().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                E entity = clazz.getDeclaredConstructor().newInstance();
                entityMapper.mapResultSetToEntity(rs, entity);
                entities.add(entity);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            LOGGER.log(Level.SEVERE, "Failed to retrieve objects: " + clazz.getSimpleName(), e);
            throw new RuntimeException("Failed to retrieve objects", e);
        }

        return entities;
    }

}
