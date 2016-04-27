package io.weba.collector.application.storage.index;

import io.weba.collector.application.storage.index.exception.ClientException;

import java.util.Map;

public interface ElasticsearchClient {
    void upsert(String name, String key, Map<String, String> data) throws ClientException;
}
