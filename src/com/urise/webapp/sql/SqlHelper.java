package com.urise.webapp.sql;

import com.urise.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    public final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String textQuery) {
        execute(textQuery, PreparedStatement::execute);
    }

    public <T> T execute(String textQuery, SqlExecutor<T> executer) {
        try (Connection conn = connectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(textQuery)) {
            return executer.execute(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
