package io.weba.visitor.infrastructure.application.storage.elasticsearch;

import io.weba.eventor.domain.device.Device;
import io.weba.eventor.domain.device.features.Features;
import io.weba.eventor.domain.device.fingerprint.Component;
import io.weba.eventor.domain.device.fingerprint.Fingerprint;
import io.weba.eventor.domain.localization.Localization;
import io.weba.visitor.domain.visit.Visit;
import io.weba.visitor.domain.visit.VisitId;
import io.weba.visitor.domain.visit.visitor.Visitor;
import io.weba.visitor.domain.visit.visitor.VisitorId;
import io.weba.visitor.infrastructure.application.serializer.GsonSerializerImpl;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VisitMapperTest {
    @Test
    public void itMapsObject() {
        Visit visit = new Visit(
                new VisitId(VisitId.fromString("591943a7-0664-4248-b2a3-6e0fac2e459d")),
                Visitor.createNewVisitorForVisit(new VisitorId(VisitorId.fromString("b2d8e9a4-c963-4b23-b564-0e2737b2c631")), new Date(), new Date()),
                new Device(new Fingerprint("#1234", new HashMap<String, Component>()), new Features()),
                new Localization("A", "B", "C", "D", "E", "F", "G")
        );

        VisitMapper mapper = new VisitMapper(new GsonSerializerImpl());
        Map<String, String> mapped = mapper.mapFields(visit);

        mapper.getExpiresAt(visit);
        mapper.reconstituteFromData(mapped);
        mapper.getFields();
        mapper.getIncrementField();
        mapper.getExpiresAt(visit);

        assertEquals("591943a7-0664-4248-b2a3-6e0fac2e459d", mapper.getIdentifier(visit));
        assertTrue(mapped.containsKey("object"));
    }
}
