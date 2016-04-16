package io.weba.visitor.application.factory.storage.elasticsearch;

import io.weba.visitor.application.storage.elasticsearch.ElasticsearchStorage;
import io.weba.visitor.infrastructure.application.storage.elasticsearch.ElasticsearchStorageImpl;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ElasticsearchStorageFactory {
    public ElasticsearchStorage create(String host, Integer port) throws UnknownHostException {
        Client client = TransportClient.builder().build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));

        return new ElasticsearchStorageImpl(client);
    }
}
