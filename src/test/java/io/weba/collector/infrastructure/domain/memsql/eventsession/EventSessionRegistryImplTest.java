package io.weba.collector.infrastructure.domain.memsql.eventsession;

import io.weba.collector.application.storage.transactional.Client;
import io.weba.collector.application.storage.transactional.exception.ClientException;
import io.weba.collector.domain.eventsession.*;
import io.weba.collector.domain.eventsession.exception.AddException;
import org.junit.Test;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

public class EventSessionRegistryImplTest {

    @Test
    public void itAddsNewEventSession() throws Exception {
        Client client = mock(Client.class);
        EventSessionMapper mapper = mock(EventSessionMapper.class);
        EventSessionRegistryImpl eventSessionRegistry = new EventSessionRegistryImpl(client, mapper);

        eventSessionRegistry.addEvent(mock(EventSession.class));

        verify(client).insert(anyString(), anyMapOf(Integer.class, Object.class));
        verify(mapper).mapForInsert(any(EventSession.class));
    }

    @Test(expected = AddException.class)
    public void itThrowsExceptionDuringAddNewEventForClientErrors()  throws Exception {
        Client client = mock(Client.class);
        EventSessionMapper mapper = mock(EventSessionMapper.class);
        EventSessionRegistryImpl eventSessionRegistry = new EventSessionRegistryImpl(client, mapper);
        when(client.insert(anyString(), anyMapOf(Integer.class, Object.class))).thenThrow(new ClientException(null));

        eventSessionRegistry.addEvent(mock(EventSession.class));
    }
}
