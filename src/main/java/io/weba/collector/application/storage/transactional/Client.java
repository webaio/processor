package io.weba.collector.application.storage.transactional;

import io.weba.collector.application.storage.transactional.exception.ClientException;

import java.util.Map;

public interface Client {
    Integer update(String sql, Map<Integer, Object> mappedObject) throws ClientException;
    Integer insert(String sql, Map<Integer, Object> mappedObject) throws ClientException;
    Map<Integer, Map<String, String>> select(String sql, Map<Integer, Object> mappedObject) throws ClientException;
    void startTransaction() throws ClientException;
    void commit() throws ClientException;
    void rollback() throws ClientException;
}
