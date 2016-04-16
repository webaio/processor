package io.weba.visitor.application.factory.storage.redis;

import io.weba.visitor.application.storage.exception.MapperException;
import io.weba.visitor.domain.visit.VisitRegistry;
import io.weba.visitor.infrastructure.domain.visit.RedisVisitRegistry;

public class RedisRegistryFactory {
    private final RedisEntityManagerFactory redisEntityManagerFactory = new RedisEntityManagerFactory();

    public VisitRegistry create(String host, Integer port) throws MapperException {
        return new RedisVisitRegistry(
                this.redisEntityManagerFactory.create(host, port)
        );
    }
}
