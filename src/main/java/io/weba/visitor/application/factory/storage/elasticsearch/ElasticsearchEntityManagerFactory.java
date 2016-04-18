package io.weba.visitor.application.factory.storage.elasticsearch;

import io.weba.visitor.application.storage.elasticsearch.ElasticsearchEntityManager;
import io.weba.visitor.application.storage.exception.MapperException;

import java.net.UnknownHostException;

public class ElasticsearchEntityManagerFactory {
    private final ElasticsearchClassMapperFactory classMapperFactory = new ElasticsearchClassMapperFactory();
    private final ElasticsearchStorageFactory storageFactory = new ElasticsearchStorageFactory();

    public ElasticsearchEntityManager create(String host, Integer port) throws MapperException, UnknownHostException {
        return new ElasticsearchEntityManager(
                this.storageFactory.create(host, port),
                this.classMapperFactory.create()
        );
    }
}
