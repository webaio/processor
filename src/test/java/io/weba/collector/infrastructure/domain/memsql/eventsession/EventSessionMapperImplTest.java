package io.weba.collector.infrastructure.domain.memsql.eventsession;

import io.weba.collector.application.serializer.Serializer;
import io.weba.collector.domain.eventsession.*;
import io.weba.collector.domain.session.*;
import io.weba.eventor.domain.device.Device;
import io.weba.eventor.domain.event.*;
import io.weba.eventor.domain.event.payload.*;
import io.weba.eventor.domain.localization.Localization;
import org.junit.Test;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EventSessionMapperImplTest {

    @Test
    public void testMapForInsert() throws Exception {
        Serializer serializer = mock(Serializer.class);
        EventSessionMapperImpl eventSessionMapper = new EventSessionMapperImpl(serializer);
        when(serializer.serialize(any())).thenReturn("{}");

        Map<Integer, Object> mapped = eventSessionMapper.mapForInsert(new EventSession(
                new EventSessionId(EventSessionId.generate()),
                new Session(
                        new SessionId(SessionId.generate()),
                        new Date(),
                        new Date(),
                        new VisitorIdentity("8e2d80e3-897d-4bc8-99a8-031b9cc55eb9")
                ),
                new Event(
                        new ID(UUID.randomUUID().toString()),
                        Type.PAGE_VIEW,
                        new PageViewPayload(
                                new PageViewPayloadAggregate(
                                        new URL("http://weba.cloud"),
                                        new UserAgent("Test"),
                                        new VisitorIdentity("8e2d80e3-897d-4bc8-99a8-031b9cc55eb9"),
                                        new TrackerId("8e2d80e3-897d-4bc8-99a8-031b9cc55eb9"),
                                        new IP("192.168.0.0")
                                )
                        ),
                        mock(Device.class),
                        mock(Localization.class),
                        new Dates(new Date(), new Date())
                )
        ));

        assertEquals(10, mapped.size());
    }
}
