package io.weba.collector.infrastructure.application.storage.elasticsearch;


import io.weba.collector.application.storage.index.ElasticsearchClient;
import io.weba.collector.application.storage.index.exception.ClientException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ElasticsearchClientImpl implements ElasticsearchClient {
    private final static String DEFAULT_INDEX_NAME = "weba.io";
    private final Client client;

    public ElasticsearchClientImpl(Client client) {
        this.client = client;
    }

    @Override
    public void upsert(String name, String key, Map<String, String> data) throws ClientException {
        IndexRequest indexRequest = new IndexRequest(DEFAULT_INDEX_NAME, name.toLowerCase(), key.toLowerCase())
                .source(data.get("object"));
        UpdateRequest updateRequest = new UpdateRequest(DEFAULT_INDEX_NAME, name.toLowerCase(), key.toLowerCase())
                .retryOnConflict(1)
                .doc(data.get("object"))
                .upsert(indexRequest);

        try {
            this.client.update(updateRequest).get();
        } catch (InterruptedException | ExecutionException exception) {
            throw new ClientException(exception);
        }
    }
}
