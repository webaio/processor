package io.weba.visitor.infrastructure.application.storage.elasticsearch;

import io.weba.visitor.application.storage.exception.ErrorDuringInsertingException;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class ElasticsearchStorageImplTest {
    @Test
    public void itInsertsAndUpdatesObjectToElasticsearch() throws ErrorDuringInsertingException {
        Client client = mock(Client.class);
        ElasticsearchStorageImpl elasticsearchStorage = new ElasticsearchStorageImpl(client);
        Map<String, String> data = new HashMap<>();
        data.put("object", "");
        ActionFuture actionFuture = mock(ActionFuture.class);
        //noinspection unchecked
        when(client.update((UpdateRequest) anyObject())).thenReturn(actionFuture);
        elasticsearchStorage.insert("weba.io", "591943a7-0664-4248-b2a3-6e0fac2e459d", data);
        verify(client, atLeastOnce()).update((UpdateRequest) anyObject());
    }

    @Test(expected = ErrorDuringInsertingException.class)
    public void itRetrievesErrorDuringInsertingToDatabase() throws ErrorDuringInsertingException {
        Client client = mock(Client.class);
        ElasticsearchStorageImpl elasticsearchStorage = new ElasticsearchStorageImpl(client);
        Map<String, String> data = new HashMap<>();
        data.put("object", "");
        doThrow(InterruptedException.class).when(client).update((UpdateRequest) anyObject());
        elasticsearchStorage.insert("weba.io", "591943a7-0664-4248-b2a3-6e0fac2e459d", data);
    }
}
