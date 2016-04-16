package io.weba.visitor.application.storage.elasticsearch;

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
import io.weba.visitor.domain.visit.Visit;
import io.weba.visitor.domain.visit.VisitId;
import io.weba.visitor.domain.visit.visitor.Visitor;
import io.weba.visitor.domain.visit.visitor.VisitorId;
import io.weba.visitor.infrastructure.application.serializer.GsonSerializerImpl;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

import static org.mockito.Mockito.*;

public class ElasticsearchUnitOfWorkTest {
    @Test
    public void itInsertsObjects() throws MapperException, ErrorDuringInsertingException, ErrorDuringCommitting {
        Visit visit = new Visit(
                new VisitId(VisitId.fromString("a2d8e9a4-c963-4b23-b564-0e2737b2c631")),
                Visitor.createNewVisitorForVisit(new VisitorId(VisitorId.fromString("b2d8e9a4-c963-4b23-b564-0e2737b2c631")), new Date(), new Date()),
                new Device(new Fingerprint("#1234", new HashMap<String, Component>()), new Features()),
                new Localization("A", "B", "C", "D", "E", "F", "G")
        );

        ClassMapper classMapper = new ClassMapper();
        Mapper visitMapper = new io.weba.visitor.infrastructure.application.storage.elasticsearch.VisitMapper(new GsonSerializerImpl());
        classMapper.addMapper(Visit.class.getName(), visitMapper);

        ElasticsearchStorage mockedElasticsearchStorage = mock(ElasticsearchStorage.class);

        ElasticsearchUnitOfWork elasticsearchUnitOfWork = new ElasticsearchUnitOfWork(mockedElasticsearchStorage, classMapper);
        elasticsearchUnitOfWork.scheduleInsert(visit);
        elasticsearchUnitOfWork.commit();

        //noinspection unchecked
        verify(mockedElasticsearchStorage).insert(anyString(), anyString(), anyMap());
    }
}
