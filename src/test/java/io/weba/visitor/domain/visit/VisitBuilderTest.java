package io.weba.visitor.domain.visit;

import io.weba.eventor.domain.device.Device;
import io.weba.eventor.domain.event.*;
import io.weba.eventor.domain.event.payload.Payload;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.domain.localization.Localization;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VisitBuilderTest {
    @Test
    public void itBuildsVisitFromEvent() throws EventorException {
        Payload payload = mock(Payload.class);
        when(payload.getVisitorIdentity()).thenReturn(new VisitorIdentity(UUID.randomUUID().toString()));
        Event event = new Event(
                new ID(UUID.randomUUID().toString()),
                Type.PAGE_VIEW,
                payload,
                mock(Device.class),
                mock(Localization.class),
                new Dates(new Date(), new Date())
        );

        VisitBuilder visitBuilder = new VisitBuilder();
        Visit visit = visitBuilder.buildFromEvent(event);

        assertEquals(visit.id.getClass().getName(), VisitId.class.getName());
    }
}
