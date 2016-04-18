package io.weba.visitor.domain.action;

import io.weba.eventor.domain.device.Device;
import io.weba.eventor.domain.device.features.Features;
import io.weba.eventor.domain.device.fingerprint.Component;
import io.weba.eventor.domain.device.fingerprint.Fingerprint;
import io.weba.eventor.domain.event.*;
import io.weba.eventor.domain.event.payload.Payload;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.domain.localization.Localization;
import io.weba.visitor.domain.visit.Visit;
import io.weba.visitor.domain.visit.VisitId;
import io.weba.visitor.domain.visit.visitor.Visitor;
import io.weba.visitor.domain.visit.visitor.VisitorId;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActionBuilderTest {
    @Test
    public void itBuildsActionForVisit() throws EventorException {
        Payload payload = mock(Payload.class);
        when(payload.getUrl()).thenReturn(new URL("http://weba.io"));
        Event event = new Event(
                new ID(UUID.randomUUID().toString()),
                Type.PAGE_VIEW,
                payload,
                mock(Device.class),
                mock(Localization.class),
                new Dates(new Date(), new Date())
        );

        Visit visit = new Visit(
                new VisitId(VisitId.generate()),
                Visitor.createNewVisitorForVisit(new VisitorId(VisitorId.fromString("b2d8e9a4-c963-4b23-b564-0e2737b2c631")), new Date(), new Date()),
                new Device(new Fingerprint("#1234", new HashMap<String, Component>()), new Features()),
                new Localization("A", "B", "C", "D", "E", "F", "G")
        );

        ActionBuilder actionBuilder = new ActionBuilder();
        Action action = actionBuilder.buildForVisit(visit, event);

        assertEquals(action.id.getClass().getName(), ActionId.class.getName());
    }
}
