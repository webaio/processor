package io.weba.visitor.infrastructure.domain.visit;

import io.weba.visitor.application.storage.elasticsearch.ElasticsearchEntityManager;
import io.weba.visitor.application.storage.exception.ErrorDuringCommitting;
import io.weba.visitor.application.storage.exception.ErrorDuringFetchingObject;
import io.weba.visitor.domain.visit.Visit;
import io.weba.visitor.domain.visit.VisitRegistry;

import java.util.Date;

public class ElasticsearchVisitRegistry implements VisitRegistry {
    private final ElasticsearchEntityManager elasticsearchEntityManager;

    public ElasticsearchVisitRegistry(ElasticsearchEntityManager elasticsearchEntityManager) {
        this.elasticsearchEntityManager = elasticsearchEntityManager;
    }

    @Override
    public void add(Visit visit) {
        this.elasticsearchEntityManager.persist(visit);
    }

    @Override
    public void update(Visit visit, Date browserUpdatedAt, Date serverUpdatedAt) {

    }

    @Override
    public Visit find(String identity) throws ErrorDuringFetchingObject {
        return null;
    }

    @Override
    public void apply() throws ErrorDuringCommitting {
        this.elasticsearchEntityManager.flush();
    }
}
