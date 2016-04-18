package io.weba.visitor.application.storage.elasticsearch;

import io.weba.visitor.application.storage.exception.ErrorDuringInsertingException;

import java.util.Map;

public interface ElasticsearchStorage {
    void insert(String name, String key, Map<String, String> data) throws ErrorDuringInsertingException;
}
