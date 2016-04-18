package io.weba.visitor.infrastructure.domain.visit;

import io.weba.eventor.domain.device.Device;
import io.weba.eventor.domain.device.features.Features;
import io.weba.eventor.domain.device.fingerprint.Component;
import io.weba.eventor.domain.device.fingerprint.Fingerprint;
import io.weba.eventor.domain.localization.Localization;
import io.weba.visitor.application.storage.elasticsearch.ElasticsearchEntityManager;
import io.weba.visitor.application.storage.exception.ErrorDuringCommitting;
import io.weba.visitor.application.storage.exception.ErrorDuringFetchingObject;
import io.weba.visitor.domain.visit.Visit;
import io.weba.visitor.domain.visit.VisitId;
import io.weba.visitor.domain.visit.visitor.Visitor;
import io.weba.visitor.domain.visit.visitor.VisitorId;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

import static org.mockito.Mockito.*;

public class ElasticsearchVisitRegistryTest {
    @Test
    public void itManagesVisitObjectOnElasticsearchStorage() throws ErrorDuringCommitting, ErrorDuringFetchingObject {
        Visit visit = new Visit(
                new VisitId(VisitId.generate()),
                Visitor.createNewVisitorForVisit(new VisitorId(VisitorId.fromString("b2d8e9a4-c963-4b23-b564-0e2737b2c631")), new Date(), new Date()),
                new Device(new Fingerprint("#1234", new HashMap<String, Component>()), new Features()),
                new Localization("A", "B", "C", "D", "E", "F", "G")
        );

        ElasticsearchEntityManager elasticsearchEntityManager = mock(ElasticsearchEntityManager.class);

        ElasticsearchVisitRegistry elasticsearchVisitRegistry = new ElasticsearchVisitRegistry(elasticsearchEntityManager);
        elasticsearchVisitRegistry.add(visit);
        elasticsearchVisitRegistry.find("");
        elasticsearchVisitRegistry.update(visit, new Date(), new Date());
        elasticsearchVisitRegistry.apply();

        verify(elasticsearchEntityManager, atLeastOnce()).flush();
        verify(elasticsearchEntityManager, atLeastOnce()).persist(visit);
    }
}
