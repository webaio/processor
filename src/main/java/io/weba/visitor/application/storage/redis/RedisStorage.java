package io.weba.visitor.application.storage.redis;

import java.util.List;
import java.util.Map;

public interface RedisStorage {
    void startTransaction();

    void commit();

    void rollback();

    void watch(String... keys);

    void increment(String name, String key, String field);

    void expire(String name, String key, Long unixTime);

    List<String> find(String name, String key, String... fields);

    void insert(String name, String key, Map<String, String> data);
}
