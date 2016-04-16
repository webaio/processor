package io.weba.visitor.infrastructure.domain.action;

import io.weba.visitor.application.storage.elasticsearch.ElasticsearchEntityManager;
import io.weba.visitor.domain.action.Action;
import io.weba.visitor.domain.action.ActionRegistry;

public class ElasticsearchActionRegistry implements ActionRegistry {
    private final ElasticsearchEntityManager elasticsearchEntityManager;

    public ElasticsearchActionRegistry(ElasticsearchEntityManager elasticsearchEntityManager) {
        this.elasticsearchEntityManager = elasticsearchEntityManager;
    }

    @Override
    public void add(Action action) {
        this.elasticsearchEntityManager.persist(action);
    }
}
