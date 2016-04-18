package io.weba.visitor.infrastructure.application.storage.elasticsearch;

import io.weba.visitor.application.storage.elasticsearch.ElasticsearchStorage;
import io.weba.visitor.application.storage.exception.ErrorDuringInsertingException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ElasticsearchStorageImpl implements ElasticsearchStorage {
    private final static String DEFAULT_INDEX_NAME = "weba.io";
    private final Client client;

    public ElasticsearchStorageImpl(Client client) {
        this.client = client;
    }

    @Override
    public void insert(String name, String key, Map<String, String> data) throws ErrorDuringInsertingException {
        IndexRequest indexRequest = new IndexRequest(DEFAULT_INDEX_NAME, name.toLowerCase(), key.toLowerCase())
                .source(data.get("object"));
        UpdateRequest updateRequest = new UpdateRequest(DEFAULT_INDEX_NAME, name.toLowerCase(), key.toLowerCase())
                .retryOnConflict(1)
                .doc(data.get("object"))
                .upsert(indexRequest);

        try {
            this.client.update(updateRequest).get();
        } catch (InterruptedException | ExecutionException exception) {
            throw new ErrorDuringInsertingException(exception);
        }
    }
}
