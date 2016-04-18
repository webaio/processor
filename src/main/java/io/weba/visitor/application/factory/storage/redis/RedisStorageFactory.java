package io.weba.visitor.application.factory.storage.redis;

import io.weba.visitor.application.storage.redis.RedisStorage;
import io.weba.visitor.infrastructure.application.storage.redis.RedisStorageImpl;
import redis.clients.jedis.Jedis;

class RedisStorageFactory {
    public RedisStorage create(String host, Integer port) {
        return new RedisStorageImpl(new Jedis(host, port));
    }
}
