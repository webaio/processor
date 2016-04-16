package io.weba.visitor.application.storage.elasticsearch;

import io.weba.visitor.application.storage.ClassMapper;
import io.weba.visitor.application.storage.Mapper;
import io.weba.visitor.application.storage.exception.ErrorDuringInsertingException;
import io.weba.visitor.application.storage.exception.MapperException;
import io.weba.visitor.domain.common.AggregateRoot;

import java.util.HashMap;
import java.util.Map;

public class ElasticsearchUnitOfWork {
    private final ElasticsearchStorage elasticsearchStorage;
    private final ClassMapper classMapper;
    private final Map<Integer, AggregateRoot> scheduledInsertions;

    public ElasticsearchUnitOfWork(ElasticsearchStorage elasticsearchStorage, ClassMapper classMapper) {
        this.elasticsearchStorage = elasticsearchStorage;
        this.classMapper = classMapper;
        this.scheduledInsertions = new HashMap<>();
    }

    public void scheduleInsert(AggregateRoot object) {
        this.scheduledInsertions.put(object.hashCode(), object);
    }

    public void commit() throws MapperException, ErrorDuringInsertingException {
        this.processInserts();
        this.clear();
    }

    void clear() {
        this.scheduledInsertions.clear();
    }

    @SuppressWarnings("unchecked")
    void processInserts() throws MapperException, ErrorDuringInsertingException {
        for (Map.Entry<Integer, AggregateRoot> entry : this.scheduledInsertions.entrySet()) {
            Mapper mapper = this.classMapper.findMapper(entry.getValue().getClass().getName());
            this.elasticsearchStorage.insert(entry.getValue().getClass().getName(), mapper.getIdentifier(entry.getValue()),
                    mapper.mapFields(entry.getValue()));
        }
    }
}
