package io.weba.eventor.domain.event;

import io.weba.eventor.domain.device.Device;
import io.weba.eventor.domain.event.payload.PageViewPayload;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.domain.localization.Localization;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertEquals;

public class EventTest {
    @Test(expected = NullPointerException.class)
    public void itThrowExceptionIfAnyArgumentIsNull() {
        new Event(null, null, null, null, null, null);
    }

    @Test()
    public void eventsWithSameIdShouldBeEquals() throws EventorException {
        UUID uuid = UUID.randomUUID();

        Event event1 = new Event(
                new ID(uuid.toString()),
                Type.PAGE_VIEW,
                mock(PageViewPayload.class),
                mock(Device.class),
                mock(Localization.class),
                mock(Dates.class)
        );

        Event event2 = new Event(
                new ID(uuid.toString()),
                Type.PAGE_VIEW,
                mock(PageViewPayload.class),
                mock(Device.class),
                mock(Localization.class),
                mock(Dates.class)
        );

        assertEquals(event1, event2);
        assertEquals(event1.hashCode(), event2.hashCode());
    }

    @Test()
    public void eventsWithDifferentIdShouldNotBeEquals() throws EventorException {
        Event event1 = new Event(
                new ID(UUID.randomUUID().toString()),
                Type.PAGE_VIEW,
                mock(PageViewPayload.class),
                mock(Device.class),
                mock(Localization.class),
                mock(Dates.class)
        );

        Event event2 = new Event(
                new ID(UUID.randomUUID().toString()),
                Type.PAGE_VIEW,
                mock(PageViewPayload.class),
                mock(Device.class),
                mock(Localization.class),
                mock(Dates.class)
        );

        assertNotEquals(event1, event2);
        assertNotEquals(event1.hashCode(), event2.hashCode());
    }
}