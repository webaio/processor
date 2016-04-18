package io.weba.visitor.infrastructure.domain.visit;

import io.weba.visitor.application.storage.exception.ErrorDuringCommitting;
import io.weba.visitor.application.storage.exception.ErrorDuringFetchingObject;
import io.weba.visitor.application.storage.redis.RedisEntityManager;
import io.weba.visitor.domain.visit.Visit;
import io.weba.visitor.domain.visit.VisitRegistry;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RedisVisitRegistry implements VisitRegistry {
    private final RedisEntityManager redisEntityManager;

    public RedisVisitRegistry(RedisEntityManager redisEntityManager) {
        this.redisEntityManager = redisEntityManager;
    }

    @Override
    public void add(Visit visit) {
        this.redisEntityManager.watch(Visit.class.getName() + "[" +visit.visitor.visitorId.toString() + "]");
        this.redisEntityManager.persist(visit);
        this.redisEntityManager.expireAt(visit);
    }

    @Override
    public void update(Visit visit, Date browserUpdatedAt, Date serverUpdatedAt) {
        this.redisEntityManager.watch(Visit.class.getName() + "[" +visit.visitor.visitorId.toString() + "]");
        this.redisEntityManager.increment(visit);

        if (visit.visitor.lastVisitAt.getTime() < browserUpdatedAt.getTime()) {
            visit.visitor.lastVisitAt.setTime(browserUpdatedAt.getTime());
            visit.visitor.serverLastVisitAt.setTime(serverUpdatedAt.getTime());
            visit.visitor.sessionEndedAt.setTime(serverUpdatedAt.getTime() + Visit.objectLifetimeInMilliseconds);
            this.redisEntityManager.update(visit, this.prepareDataForUpdate(visit));
            this.redisEntityManager.expireAt(visit);
        }
    }

    @Override
    public Visit find(String identity) throws ErrorDuringFetchingObject {
        return (Visit) this.redisEntityManager.find(Visit.class.getName(), identity);
    }

    @Override
    public void apply() throws ErrorDuringCommitting {
        this.redisEntityManager.flush();
    }

    private Map<String, String> prepareDataForUpdate(Visit visit) {
        Map<String, String> dataToUpdate = new HashMap<>();
        dataToUpdate.put("lastVisitAt", String.valueOf(visit.visitor.lastVisitAt.getTime()));
        dataToUpdate.put("serverLastVisitAt", String.valueOf(visit.visitor.serverLastVisitAt.getTime()));
        dataToUpdate.put("sessionEndedAt", String.valueOf(visit.visitor.sessionEndedAt.getTime()));

        return dataToUpdate;
    }
}
