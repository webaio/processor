package io.weba.visitor.infrastructure.application.storage.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class RedisStorageImplTest {
    private final Jedis jedis;
    private final Transaction transaction;
    private final RedisStorageImpl transactionalRedisStorage;

    public RedisStorageImplTest() {
        this.jedis = mock(Jedis.class);
        this.transaction = mock(Transaction.class);
        when(this.jedis.multi()).thenReturn(this.transaction);
        this.transactionalRedisStorage = new RedisStorageImpl(this.jedis);
    }

    @Test
    public void itManagesRecordsInTransaction() {
        Long date = new Date().getTime();
        Map<String, String> data = new HashMap<>();
        String[] keys = new String[]{"key"};
        this.transactionalRedisStorage.watch(keys);
        this.transactionalRedisStorage.startTransaction();
        this.transactionalRedisStorage.insert("test", "test", data);
        this.transactionalRedisStorage.increment("test", "test", "test");
        this.transactionalRedisStorage.expire("test", "test", date);
        this.transactionalRedisStorage.commit();
        verify(this.transaction, atLeastOnce()).hmset("test[test]", data);
        verify(this.transaction, atLeastOnce()).hincrBy("test[test]", "test", 1);
        verify(this.transaction, atLeastOnce()).pexpireAt("test[test]", date);
        verify(this.jedis, atLeastOnce()).watch(keys);
    }

    @Test
    public void itIsAbleToRollbackTransaction() {
        this.transactionalRedisStorage.startTransaction();
        this.transactionalRedisStorage.rollback();
        verify(this.transaction, atLeastOnce()).discard();
    }

    @Test
    public void itFindsRecordInRedis() {
        String[] fields = {"test"};
        this.transactionalRedisStorage.find("test", "test", fields);
        verify(this.jedis).hmget("test[test]", fields);
    }
}
