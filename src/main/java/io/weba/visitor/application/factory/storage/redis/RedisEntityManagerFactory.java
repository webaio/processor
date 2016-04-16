package io.weba.visitor.application.factory.storage.redis;

import io.weba.visitor.application.storage.exception.MapperException;
import io.weba.visitor.application.storage.redis.RedisEntityManager;

class RedisEntityManagerFactory {
    private final RedisClassMapperFactory classMapperFactory = new RedisClassMapperFactory();
    private final RedisStorageFactory storageFactory = new RedisStorageFactory();

    public RedisEntityManager create(String host, Integer port) throws MapperException {
        return new RedisEntityManager(
                this.storageFactory.create(host, port),
                this.classMapperFactory.create()
        );
    }
}
