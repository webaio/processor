package io.weba.visitor.infrastructure.domain.visit;

import io.weba.eventor.domain.device.Device;
import io.weba.eventor.domain.device.features.Features;
import io.weba.eventor.domain.device.fingerprint.Component;
import io.weba.eventor.domain.device.fingerprint.Fingerprint;
import io.weba.eventor.domain.localization.Localization;
import io.weba.visitor.application.storage.exception.ErrorDuringCommitting;
import io.weba.visitor.application.storage.exception.ErrorDuringFetchingObject;
import io.weba.visitor.application.storage.redis.RedisEntityManager;
import io.weba.visitor.domain.visit.Visit;
import io.weba.visitor.domain.visit.VisitId;
import io.weba.visitor.domain.visit.visitor.Visitor;
import io.weba.visitor.domain.visit.visitor.VisitorId;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

import static org.mockito.Mockito.*;

public class RedisVisitRegistryTest {
    @Test
    public void itManagesVisitInRedisStorage() throws ErrorDuringCommitting, ErrorDuringFetchingObject {
        Visit visit = new Visit(
                new VisitId(VisitId.fromString("b2d8e9a4-c963-4b23-b564-0e2737b2c631")),
                Visitor.createNewVisitorForVisit(new VisitorId(VisitorId.fromString("b2d8e9a4-c963-4b23-b564-0e2737b2c631")), new Date(), new Date()),
                new Device(new Fingerprint("#1234", new HashMap<String, Component>()), new Features()),
                new Localization("A", "B", "C", "D", "E", "F", "G")
        );

        RedisEntityManager redisEntityManager = mock(RedisEntityManager.class);
        RedisVisitRegistry redisVisitRegistry = new RedisVisitRegistry(redisEntityManager);

        redisVisitRegistry.add(visit);
        redisVisitRegistry.apply();

        verify(redisEntityManager, atLeastOnce()).watch("io.weba.visitor.domain.visit.Visit[b2d8e9a4-c963-4b23-b564-0e2737b2c631]");
        verify(redisEntityManager, atLeastOnce()).persist(visit);
        verify(redisEntityManager, atLeastOnce()).expireAt(visit);
        verify(redisEntityManager, atLeastOnce()).flush();

        Date test = new Date();
        Date browserUpdatedAt = new Date();
        browserUpdatedAt.setTime(test.getTime() + 100);
        Date serverUpdatedAt = new Date();
        serverUpdatedAt.setTime(test.getTime() + 100);

        redisVisitRegistry.update(visit, browserUpdatedAt, serverUpdatedAt);

        verify(redisEntityManager, atLeastOnce()).watch("io.weba.visitor.domain.visit.Visit[b2d8e9a4-c963-4b23-b564-0e2737b2c631]");
        //noinspection unchecked
        verify(redisEntityManager, atLeastOnce()).update(any(Visit.class), anyMap());
        verify(redisEntityManager, atLeastOnce()).expireAt(visit);
        verify(redisEntityManager, atLeastOnce()).increment(visit);

        redisVisitRegistry.find("b2d8e9a4-c963-4b23-b564-0e2737b2c631");

        verify(redisEntityManager, atLeastOnce()).find(Visit.class.getName(), "b2d8e9a4-c963-4b23-b564-0e2737b2c631");
    }

    @Test
    public void itDoesNotUpdatedVisitDateForVisitsFromPast() {
        Visit visit = new Visit(
                new VisitId(VisitId.fromString("b2d8e9a4-c963-4b23-b564-0e2737b2c631")),
                Visitor.createNewVisitorForVisit(new VisitorId(VisitorId.fromString("b2d8e9a4-c963-4b23-b564-0e2737b2c631")), new Date(), new Date()),
                new Device(new Fingerprint("#1234", new HashMap<String, Component>()), new Features()),
                new Localization("A", "B", "C", "D", "E", "F", "G")
        );

        RedisEntityManager redisEntityManager = mock(RedisEntityManager.class);
        RedisVisitRegistry redisVisitRegistry = new RedisVisitRegistry(redisEntityManager);

        Date test = new Date();
        Date browserUpdatedAt = new Date();
        browserUpdatedAt.setTime(test.getTime() - 10000);
        Date serverUpdatedAt = new Date();
        serverUpdatedAt.setTime(test.getTime() - 10000);

        redisVisitRegistry.update(visit, browserUpdatedAt, serverUpdatedAt);

        verify(redisEntityManager, atLeastOnce()).watch("io.weba.visitor.domain.visit.Visit[b2d8e9a4-c963-4b23-b564-0e2737b2c631]");
        verify(redisEntityManager, atLeastOnce()).increment(visit);
        //noinspection unchecked
        verify(redisEntityManager, never()).update(any(Visit.class), anyMap());
        verify(redisEntityManager, never()).expireAt(visit);
    }
}
