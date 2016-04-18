package io.weba.visitor.application.storage.elasticsearch;

import io.weba.visitor.application.storage.ClassMapper;
import io.weba.visitor.application.storage.exception.ErrorDuringCommitting;
import io.weba.visitor.application.storage.exception.ErrorDuringInsertingException;
import io.weba.visitor.application.storage.exception.MapperException;
import io.weba.visitor.domain.common.AggregateRoot;

public class ElasticsearchEntityManager {
    private final ElasticsearchUnitOfWork elasticsearchUnitOfWork;

    public ElasticsearchEntityManager(ElasticsearchStorage elasticsearchStorage, ClassMapper classMapper) {
        this.elasticsearchUnitOfWork = new ElasticsearchUnitOfWork(elasticsearchStorage, classMapper);
    }

    public void persist(AggregateRoot object) {
        this.elasticsearchUnitOfWork.scheduleInsert(object);
    }

    public void flush() throws ErrorDuringCommitting {
        try {
            this.elasticsearchUnitOfWork.commit();
        } catch (MapperException | ErrorDuringInsertingException e) {
            throw new ErrorDuringCommitting(e);
        }
    }
}
