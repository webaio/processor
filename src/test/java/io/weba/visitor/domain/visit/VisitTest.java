package io.weba.visitor.domain.visit;

import io.weba.eventor.domain.device.Device;
import io.weba.eventor.domain.device.features.Features;
import io.weba.eventor.domain.device.fingerprint.Component;
import io.weba.eventor.domain.device.fingerprint.Fingerprint;
import io.weba.eventor.domain.localization.Localization;
import io.weba.visitor.domain.visit.visitor.Visitor;
import io.weba.visitor.domain.visit.visitor.VisitorId;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class VisitTest {
    @Test
    public void createNewVisitBasedOnEvent() {
        Visit visit = new Visit(
                new VisitId(VisitId.generate()),
                Visitor.createNewVisitorForVisit(new VisitorId(VisitorId.fromString("b2d8e9a4-c963-4b23-b564-0e2737b2c631")), new Date(), new Date()),
                new Device(new Fingerprint("#1234", new HashMap<String, Component>()), new Features()),
                new Localization("A", "B", "C", "D", "E", "F", "G")
        );

        assertEquals(visit.visitor.numberOfActions.intValue(), 1);
        assertEquals(visit.visitor.visitorId.toString(), "b2d8e9a4-c963-4b23-b564-0e2737b2c631");
    }

    @Test
    public void createExistingVisitFromStorage() {
        Visit visit = new Visit(
                new VisitId(VisitId.generate()),
                Visitor.loadFromStorage(new VisitorId(VisitorId.generate()), 2, new Date(), new Date(), new Date(), new Date(), new Date()),
                new Device(new Fingerprint("#1234", new HashMap<String, Component>()), new Features()),
                new Localization("A", "B", "C", "D", "E", "F", "G")
        );

        assertEquals(visit.visitor.numberOfActions.intValue(), 2);
    }
}
