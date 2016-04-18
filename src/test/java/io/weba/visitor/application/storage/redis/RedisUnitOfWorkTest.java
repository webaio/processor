package io.weba.visitor.application.storage.redis;

import io.weba.eventor.domain.device.Device;
import io.weba.eventor.domain.device.features.Features;
import io.weba.eventor.domain.device.fingerprint.Component;
import io.weba.eventor.domain.device.fingerprint.Fingerprint;
import io.weba.eventor.domain.localization.Localization;
import io.weba.visitor.application.storage.ClassMapper;
import io.weba.visitor.application.storage.Mapper;
import io.weba.visitor.application.storage.exception.ErrorDuringCommitting;
import io.weba.visitor.application.storage.exception.ErrorDuringInsertingException;
import io.weba.visitor.application.storage.exception.MapperException;
import io.weba.visitor.domain.common.AggregateRoot;
import io.weba.visitor.domain.visit.Visit;
import io.weba.visitor.domain.visit.VisitId;
import io.weba.visitor.domain.visit.visitor.Visitor;
import io.weba.visitor.domain.visit.visitor.VisitorId;
import io.weba.visitor.infrastructure.application.serializer.GsonSerializerImpl;
import io.weba.visitor.infrastructure.application.storage.redis.VisitMapper;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class RedisUnitOfWorkTest {
    @Test
    public void itManagesObjectsInTransactionBlock() throws MapperException, ErrorDuringInsertingException, ErrorDuringCommitting {
        Visit visit = new Visit(
                new VisitId(VisitId.fromString("a2d8e9a4-c963-4b23-b564-0e2737b2c631")),
                Visitor.createNewVisitorForVisit(new VisitorId(VisitorId.fromString("b2d8e9a4-c963-4b23-b564-0e2737b2c631")), new Date(), new Date()),
                new Device(new Fingerprint("#1234", new HashMap<String, Component>()), new Features()),
                new Localization("A", "B", "C", "D", "E", "F", "G")
        );

        ClassMapper classMapper = new ClassMapper();
        Mapper visitMapper = new VisitMapper(new GsonSerializerImpl());
        classMapper.addMapper(Visit.class.getName(), visitMapper);

        RedisStorage mockedStorage = mock(RedisStorage.class);

        RedisUnitOfWork unitOfWork = new RedisUnitOfWork(mockedStorage, classMapper);
        unitOfWork.scheduleInsert(visit);
        unitOfWork.scheduleExpires(visit);
        unitOfWork.scheduleIncrement(visit);
        Map<String, String> dataToUpdate = new HashMap<>();
        dataToUpdate.put("lastVisitAt", String.valueOf(new Date().getTime()));
        unitOfWork.scheduleUpdate(visit, dataToUpdate);
        unitOfWork.commit();

        List<String> returnedListFromFind = new ArrayList<>();
        returnedListFromFind.add("a2d8e9a4-c963-4b23-b564-0e2737b2c631");
        returnedListFromFind.add("a2d8e9a4-c963-4b23-b564-0e2737b2c632");
        returnedListFromFind.add("2");
        returnedListFromFind.add("1460669036634");
        returnedListFromFind.add("1460669036634");
        returnedListFromFind.add("1460669036634");
        returnedListFromFind.add("1460669036634");
        returnedListFromFind.add("1460669036634");
        returnedListFromFind.add("{}");
        returnedListFromFind.add("{}");

        when(mockedStorage.find(Visit.class.getName(), "a2d8e9a4-c963-4b23-b564-0e2737b2c631", "id", "visitorId", "numberOfActions", "firstVisitAt", "lastVisitAt", "serverFirstVisitAt", "serverLastVisitAt", "sessionEndedAt", "localization", "device")).thenReturn(returnedListFromFind);

        unitOfWork.reconstitute(Visit.class.getName(), "a2d8e9a4-c963-4b23-b564-0e2737b2c631");
    }

    @Test(expected = ErrorDuringCommitting.class)
    public void itDoesRollbackAfterThrowException() throws MapperException, ErrorDuringCommitting {
        Visit visit = new Visit(
                new VisitId(VisitId.fromString("a2d8e9a4-c963-4b23-b564-0e2737b2c631")),
                Visitor.createNewVisitorForVisit(new VisitorId(VisitorId.fromString("b2d8e9a4-c963-4b23-b564-0e2737b2c631")), new Date(), new Date()),
                new Device(new Fingerprint("#1234", new HashMap<String, Component>()), new Features()),
                new Localization("A", "B", "C", "D", "E", "F", "G")
        );

        ClassMapper classMapper = new ClassMapper();
        Mapper visitMapper = new VisitMapper(new GsonSerializerImpl());
        classMapper.addMapper(Visit.class.getName(), visitMapper);

        RedisStorage mockedStorage = mock(RedisStorage.class);
        //noinspection unchecked
        doThrow(Exception.class).when(mockedStorage).insert(anyString(), anyString(), anyMap());

        RedisUnitOfWork unitOfWork = new RedisUnitOfWork(mockedStorage, classMapper);
        unitOfWork.scheduleInsert(visit);
        unitOfWork.commit();
        verify(mockedStorage, atLeastOnce()).rollback();
    }

    @Test
    public void itReturnsNullIfRecordDoesNotExist() throws MapperException {
        ClassMapper classMapper = new ClassMapper();
        Mapper visitMapper = new VisitMapper(new GsonSerializerImpl());
        classMapper.addMapper(Visit.class.getName(), visitMapper);

        RedisStorage mockedStorage = mock(RedisStorage.class);

        RedisUnitOfWork unitOfWork = new RedisUnitOfWork(mockedStorage, classMapper);

        List<String> returnedListFromFind = new ArrayList<>();

        when(mockedStorage.find(Visit.class.getName(), "a2d8e9a4-c963-4b23-b564-0e2737b2c631", "id", "visitorId", "numberOfActions", "firstVisitAt", "lastVisitAt", "serverFirstVisitAt", "serverLastVisitAt", "sessionEndedAt", "localization", "device")).thenReturn(returnedListFromFind);

        AggregateRoot reconstitute = unitOfWork.reconstitute(Visit.class.getName(), "a2d8e9a4-c963-4b23-b564-0e2737b2c631");

        assertNull(reconstitute);
    }
}
