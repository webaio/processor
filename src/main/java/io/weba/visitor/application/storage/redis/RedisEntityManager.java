package io.weba.visitor.application.storage.redis;

import io.weba.visitor.application.storage.ClassMapper;
import io.weba.visitor.application.storage.exception.ErrorDuringCommitting;
import io.weba.visitor.application.storage.exception.ErrorDuringFetchingObject;
import io.weba.visitor.application.storage.exception.MapperException;
import io.weba.visitor.domain.common.AggregateRoot;

import java.util.Map;

public class RedisEntityManager {
    private final RedisStorage storage;
    private final RedisUnitOfWork unitOfWork;

    public RedisEntityManager(RedisStorage storage, ClassMapper classMapper) {
        this.unitOfWork = new RedisUnitOfWork(storage, classMapper);
        this.storage = storage;
    }

    public AggregateRoot find(String className, String key) throws ErrorDuringFetchingObject {
        try {
            return this.unitOfWork.reconstitute(className, key);
        } catch (MapperException e) {
            throw new ErrorDuringFetchingObject(e);
        }
    }

    public void persist(AggregateRoot object) {
        this.unitOfWork.scheduleInsert(object);
    }

    public void update(AggregateRoot object, Map<String, String> data) {
        this.unitOfWork.scheduleUpdate(object, data);
    }

    public void expireAt(AggregateRoot object) {
        this.unitOfWork.scheduleExpires(object);
    }

    public void watch(String... keys) {
        this.storage.watch(keys);
    }

    public void increment(AggregateRoot object) {
        this.unitOfWork.scheduleIncrement(object);
    }

    public void flush() throws ErrorDuringCommitting {
        this.unitOfWork.commit();
    }
}
