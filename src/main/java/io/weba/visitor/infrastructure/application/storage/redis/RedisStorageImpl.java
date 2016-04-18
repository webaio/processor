package io.weba.visitor.infrastructure.application.storage.redis;

import io.weba.visitor.application.storage.redis.RedisStorage;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.Map;

public class RedisStorageImpl implements RedisStorage {
    private final Jedis jedis;
    private Transaction transaction;

    public RedisStorageImpl(Jedis jedis) {
        this.jedis = jedis;
    }

    public void insert(String name, String key, Map<String, String> data) {
        this.transaction.hmset(this.getKey(name, key), data);
    }

    public void increment(String name, String key, String field) {
        this.transaction.hincrBy(this.getKey(name, key), field, 1);
    }

    public void expire(String name, String key, Long unixTime) {
        this.transaction.pexpireAt(this.getKey(name, key), unixTime);
    }

    public List<String> find(String name, String key, String[] fields) {
        return this.jedis.hmget(this.getKey(name, key), fields);
    }

    public void startTransaction() {
        this.transaction = jedis.multi();
    }

    public void commit() {
        this.transaction.exec();
    }

    public void rollback() {
        this.transaction.discard();
    }

    public void watch(String... keys) {
        this.jedis.watch(keys);
    }

    private String getKey(String name, String key) {
        return name + "[" + key + "]";
    }
}
