package io.weba.collector.infrastructure.application.collector;

import io.weba.collector.application.collector.CollectorException;
import io.weba.collector.application.storage.transactional.Client;
import io.weba.collector.domain.eventaggregator.EventAggregator;
import io.weba.collector.domain.eventaggregator.EventAggregatorRegistry;
import io.weba.collector.domain.eventsession.EventSession;
import io.weba.collector.domain.eventsession.EventSessionRegistry;
import io.weba.collector.domain.session.Session;
import io.weba.collector.domain.session.SessionId;
import io.weba.collector.domain.session.SessionRegistry;
import io.weba.collector.domain.session.exception.FindException;
import io.weba.eventor.domain.device.Device;
import io.weba.eventor.domain.event.*;
import io.weba.eventor.domain.event.payload.*;
import io.weba.eventor.domain.localization.Localization;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class CollectorImplTest {

    @Test
    public void itCollectsNewVisitOnPage() throws Exception {
        Client client = mock(Client.class);
        SessionRegistry sessionRegistry = mock(SessionRegistry.class);
        EventSessionRegistry eventSessionRegistry = mock(EventSessionRegistry.class);
        EventAggregatorRegistry eventAggregatorRegistry = mock(EventAggregatorRegistry.class);
        CollectorImpl collector = new CollectorImpl(client, sessionRegistry, eventSessionRegistry, eventAggregatorRegistry);

        when(sessionRegistry.findActiveSession(any(Date.class), any(VisitorIdentity.class))).thenReturn(null);

        collector.collect(this.getMockEvent());

        verify(sessionRegistry, atLeastOnce()).addNewSession(any(Session.class));
        verify(eventAggregatorRegistry, atLeastOnce()).add(any(EventAggregator.class));
        verify(eventSessionRegistry, atLeastOnce()).addEvent(any(EventSession.class));
        verify(client, atLeastOnce()).commit();
    }

    @Test
    public void itCollectsExistingVisitOnPage() throws Exception {
        Client client = mock(Client.class);
        SessionRegistry sessionRegistry = mock(SessionRegistry.class);
        EventSessionRegistry eventSessionRegistry = mock(EventSessionRegistry.class);
        EventAggregatorRegistry eventAggregatorRegistry = mock(EventAggregatorRegistry.class);
        CollectorImpl collector = new CollectorImpl(client, sessionRegistry, eventSessionRegistry, eventAggregatorRegistry);

        when(sessionRegistry.findActiveSession(any(Date.class), any(VisitorIdentity.class))).thenReturn(mock(Session.class));

        collector.collect(this.getMockEvent());

        verify(sessionRegistry, atLeastOnce()).renewActiveSession(any(Date.class), any(VisitorIdentity.class));
        verify(eventAggregatorRegistry, atLeastOnce()).update(any(SessionId.class), any(VisitorIdentity.class));
        verify(eventSessionRegistry, atLeastOnce()).addEvent(any(EventSession.class));
        verify(client, atLeastOnce()).commit();
    }

    @Test(expected = CollectorException.class)
    public void itRollbacksIfAnyExceptionWasMet() throws Exception {
        Client client = mock(Client.class);
        SessionRegistry sessionRegistry = mock(SessionRegistry.class);
        EventSessionRegistry eventSessionRegistry = mock(EventSessionRegistry.class);
        EventAggregatorRegistry eventAggregatorRegistry = mock(EventAggregatorRegistry.class);
        CollectorImpl collector = new CollectorImpl(client, sessionRegistry, eventSessionRegistry, eventAggregatorRegistry);

        when(sessionRegistry.findActiveSession(any(Date.class), any(VisitorIdentity.class))).thenThrow(new FindException(null));

        collector.collect(this.getMockEvent());

        verify(client, atLeastOnce()).rollback();
    }

    private Event getMockEvent() throws Exception {
        return new Event(
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
        );
    }
}
