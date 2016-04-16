package io.weba.visitor.application.factory.storage.elasticsearch;

import io.weba.visitor.application.storage.elasticsearch.ElasticsearchEntityManager;
import io.weba.visitor.application.storage.exception.MapperException;
import io.weba.visitor.domain.action.ActionRegistry;
import io.weba.visitor.domain.visit.VisitRegistry;
import io.weba.visitor.infrastructure.domain.action.ElasticsearchActionRegistry;
import io.weba.visitor.infrastructure.domain.visit.ElasticsearchVisitRegistry;

import java.net.UnknownHostException;

public class ElasticsearchRegistryFactory {
    private final ElasticsearchEntityManager elasticsearchEntityManager;

    public ElasticsearchRegistryFactory(String host, Integer port) throws UnknownHostException, MapperException {
        this.elasticsearchEntityManager = new ElasticsearchEntityManagerFactory().create(host, port);
    }

    public VisitRegistry createVisitRegistry() {
        return new ElasticsearchVisitRegistry(
                this.elasticsearchEntityManager
        );
    }

    public ActionRegistry createActionRegistry() {
        return new ElasticsearchActionRegistry(
                this.elasticsearchEntityManager
        );
    }
}
