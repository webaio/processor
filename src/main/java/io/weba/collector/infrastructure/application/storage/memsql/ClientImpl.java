package io.weba.collector.infrastructure.application.storage.memsql;

import io.weba.collector.application.storage.transactional.Client;
import io.weba.collector.application.storage.transactional.exception.ClientException;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ClientImpl implements Client {
    protected final Connection connection;

    public ClientImpl(String dbHost, Integer dbPort, String dbName, String username, String password) throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", dbHost, dbPort, dbName);
        this.connection = DriverManager.getConnection(url, username, password);
    }

    @Override
    public Integer update(String sql, Map<Integer, Object> mappedObject) throws ClientException {
        return this.doInsertUpdate(sql, mappedObject);
    }

    @Override
    public Integer insert(String sql, Map<Integer, Object> mappedObject) throws ClientException {
        return this.doInsertUpdate(sql, mappedObject);
    }

    @Override
    public Map<Integer, Map<String, String>> select(String sql, Map <Integer, Object> mappedObject) throws ClientException {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            for (Map.Entry<Integer, Object> entry : mappedObject.entrySet()) {
                preparedStatement.setObject(entry.getKey(), entry.getValue());
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            Map<String, String> rowResult = new HashMap<>();
            Map<Integer, Map<String, String>> result = new HashMap<>();

            while (resultSet.next()) {
                ResultSetMetaData metaData = resultSet.getMetaData();

                for (Integer i = 1; i <= metaData.getColumnCount(); i++) {
                    rowResult.put(metaData.getColumnName(i), resultSet.getString(i));
                }

                result.put(resultSet.getRow() - 1, rowResult);
            }

            return result;
        } catch (SQLException e) {
            throw new ClientException(e);
        }
    }

    @Override
    public void startTransaction() throws ClientException {
        try {
            this.connection.setAutoCommit(false);
            this.connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (SQLException e) {
            throw new ClientException(e);
        }
    }

    @Override
    public void commit() throws ClientException {
        try {
            this.connection.commit();
        } catch (SQLException e) {
            throw new ClientException(e);
        }
    }

    @Override
    public void rollback() throws ClientException {
        try {
            this.connection.rollback();
        } catch (SQLException e) {
            throw new ClientException(e);
        }
    }

    private Integer doInsertUpdate(String sql, Map<Integer, Object> mappedObject) throws ClientException {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            for (Map.Entry<Integer, Object> entry : mappedObject.entrySet()) {
                preparedStatement.setObject(entry.getKey(), entry.getValue());
            }

            return preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new ClientException(ex);
        }
    }
}
