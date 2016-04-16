package io.weba.visitor.application.storage.redis;

import io.weba.visitor.application.storage.ChangeSet;
import io.weba.visitor.application.storage.ClassMapper;
import io.weba.visitor.application.storage.Mapper;
import io.weba.visitor.application.storage.exception.ErrorDuringCommitting;
import io.weba.visitor.application.storage.exception.ErrorDuringInsertingException;
import io.weba.visitor.application.storage.exception.MapperException;
import io.weba.visitor.domain.common.AggregateRoot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisUnitOfWork {
    private final RedisStorage storage;
    private final ClassMapper classMapper;
    private final Map<Integer, AggregateRoot> scheduledIncrementation;
    private final Map<Integer, AggregateRoot> scheduledExpires;
    private final Map<Integer, ChangeSet> scheduledUpdates;
    private final Map<Integer, AggregateRoot> scheduledInsertions;

    public RedisUnitOfWork(RedisStorage storage, ClassMapper classMapper) {
        this.storage = storage;
        this.classMapper = classMapper;
        this.scheduledIncrementation = new HashMap<>();
        this.scheduledExpires = new HashMap<>();
        this.scheduledUpdates = new HashMap<>();
        this.scheduledInsertions = new HashMap<>();
    }

    public void scheduleUpdate(AggregateRoot object, Map<String, String> data) {
        this.scheduledUpdates.put(object.hashCode(), new ChangeSet(object, data));
    }

    public void scheduleExpires(AggregateRoot object) {
        this.scheduledExpires.put(object.hashCode(), object);
    }

    public void scheduleIncrement(AggregateRoot object) {
        this.scheduledIncrementation.put(object.hashCode(), object);
    }

    public void scheduleInsert(AggregateRoot object) {
        this.scheduledInsertions.put(object.hashCode(), object);
    }

    public void commit() throws ErrorDuringCommitting {
        this.storage.startTransaction();

        try {
            this.processInserts();
            this.processUpdates();
            this.processIncrements();
            this.processExpires();
            this.storage.commit();
            this.clear();
        } catch (Exception ex) {
            this.storage.rollback();
            this.clear();
            throw new ErrorDuringCommitting(ex);
        }
    }

    void clear() {
        this.scheduledIncrementation.clear();
        this.scheduledExpires.clear();
        this.scheduledUpdates.clear();
    }

    public AggregateRoot reconstitute(String className, String key) throws MapperException {
        Mapper mapper = this.classMapper.findMapper(className);
        List<String> list = this.storage.find(className, key, mapper.getFields());
        Map<String, String> mappedValues = new HashMap<>();
        Integer currentIndex = 0;

        for (String item : list) {
            String fieldName = mapper.getFields()[currentIndex++];
            mappedValues.put(fieldName, item);
        }

        if (mappedValues.get("id") == null) {
            return null;
        }

        return (AggregateRoot) mapper.reconstituteFromData(mappedValues);
    }

    @SuppressWarnings("unchecked")
    private void processIncrements() throws MapperException {
        for (Map.Entry<Integer, AggregateRoot> entry : this.scheduledIncrementation.entrySet()) {
            Mapper mapper = this.classMapper.findMapper(entry.getValue().getClass().getName());
            this.storage.increment(entry.getValue().getClass().getName(), mapper.getIdentifier(entry.getValue()),
                    mapper.getIncrementField());
        }
    }

    @SuppressWarnings("unchecked")
    private void processExpires() throws MapperException {
        for (Map.Entry<Integer, AggregateRoot> entry : this.scheduledExpires.entrySet()) {
            Mapper mapper = this.classMapper.findMapper(entry.getValue().getClass().getName());
            this.storage.expire(entry.getValue().getClass().getName(), mapper.getIdentifier(entry.getValue()),
                    mapper.getExpiresAt(entry.getValue()));
        }
    }

    @SuppressWarnings("unchecked")
    private void processUpdates() throws MapperException, ErrorDuringInsertingException {
        for (Map.Entry<Integer, ChangeSet> entry : this.scheduledUpdates.entrySet()) {
            Mapper mapper = this.classMapper.findMapper(entry.getValue().aggregateRoot.getClass().getName());
            this.storage.insert(entry.getValue().aggregateRoot.getClass().getName(),
                    mapper.getIdentifier(entry.getValue().aggregateRoot), entry.getValue().dataToUpdate);
        }
    }

    @SuppressWarnings("unchecked")
    void processInserts() throws MapperException, ErrorDuringInsertingException {
        for (Map.Entry<Integer, AggregateRoot> entry : this.scheduledInsertions.entrySet()) {
            Mapper mapper = this.classMapper.findMapper(entry.getValue().getClass().getName());
            this.storage.insert(entry.getValue().getClass().getName(), mapper.getIdentifier(entry.getValue()),
                    mapper.mapFields(entry.getValue()));
        }
    }
}
