package io.weba.collector.infrastructure.domain.memsql.eventaggregator;

import io.weba.collector.application.storage.transactional.Client;
import io.weba.collector.application.storage.transactional.exception.ClientException;
import io.weba.collector.domain.eventaggregator.*;
import io.weba.collector.domain.eventaggregator.exceptions.*;
import io.weba.collector.domain.session.*;
import io.weba.eventor.domain.event.VisitorIdentity;
import org.junit.Test;

import java.util.Date;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

public class EventAggregatorRegistryImplTest {

    @Test
    public void itAddsNewEventAggregator() throws Exception {
        Client client = mock(Client.class);
        EventAggregatorMapper mapper = mock(EventAggregatorMapper.class);
        EventAggregatorRegistryImpl eventAggregatorRegistry = new EventAggregatorRegistryImpl(client, mapper);

        eventAggregatorRegistry.add(new EventAggregator(
                new EventAggregatorId(EventAggregatorId.generate()),
                new Session(new SessionId(SessionId.generate()), new Date(), new VisitorIdentity("8e2d80e3-897d-4bc8-99a8-031b9cc55eb9")),
                new VisitorIdentity("8e2d80e3-897d-4bc8-99a8-031b9cc55eb9")
        ));

        verify(client).insert(anyString(), anyMapOf(Integer.class, Object.class));
        verify(mapper).mapForInsert(any(EventAggregator.class));
    }

    @Test
    public void itUpdatesExistingEventAggregator() throws Exception {
        Client client = mock(Client.class);
        EventAggregatorMapper mapper = mock(EventAggregatorMapper.class);
        EventAggregatorRegistryImpl eventAggregatorRegistry = new EventAggregatorRegistryImpl(client, mapper);

        eventAggregatorRegistry.update(new SessionId(SessionId.generate()), new VisitorIdentity("8e2d80e3-897d-4bc8-99a8-031b9cc55eb9"));

        verify(client).update(anyString(), anyMapOf(Integer.class, Object.class));
        verify(mapper).mapForUpdate(any(SessionId.class), any(VisitorIdentity.class));
    }

    @Test(expected = UpdateException.class)
    public void itThrowsExceptionDuringUpdateForClientErrors() throws Exception {
        Client client = mock(Client.class);
        EventAggregatorMapper mapper = mock(EventAggregatorMapper.class);
        EventAggregatorRegistryImpl eventAggregatorRegistry = new EventAggregatorRegistryImpl(client, mapper);
        when(client.update(anyString(), anyMapOf(Integer.class, Object.class))).thenThrow(new ClientException(null));

        eventAggregatorRegistry.update(new SessionId(SessionId.generate()), new VisitorIdentity("8e2d80e3-897d-4bc8-99a8-031b9cc55eb9"));
    }

    @Test(expected = AddException.class)
    public void itThrowsExceptionDuringAddForClientErrors() throws Exception {
        Client client = mock(Client.class);
        EventAggregatorMapper mapper = mock(EventAggregatorMapper.class);
        EventAggregatorRegistryImpl eventAggregatorRegistry = new EventAggregatorRegistryImpl(client, mapper);
        when(client.insert(anyString(), anyMapOf(Integer.class, Object.class))).thenThrow(new ClientException(null));

        eventAggregatorRegistry.add(mock(EventAggregator.class));
    }
}
