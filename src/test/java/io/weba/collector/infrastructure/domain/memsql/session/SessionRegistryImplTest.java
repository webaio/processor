package io.weba.collector.infrastructure.domain.memsql.session;

import io.weba.collector.application.storage.transactional.Client;
import io.weba.collector.application.storage.transactional.exception.ClientException;
import io.weba.collector.domain.session.*;
import io.weba.collector.domain.session.exception.*;
import io.weba.eventor.domain.event.VisitorIdentity;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class SessionRegistryImplTest {

    @Test
    public void itAddsNewSession() throws Exception {
        Client client = mock(Client.class);
        SessionMapper mapper = mock(SessionMapper.class);
        SessionRegistryImpl sessionRegistry = new SessionRegistryImpl(client, mapper);

        sessionRegistry.addNewSession(new Session(
                new SessionId(SessionId.generate()),
                new Date(),
                new Date(),
                new VisitorIdentity("8e2d80e3-897d-4bc8-99a8-031b9cc55eb9")
        ));

        verify(client).insert(anyString(), anyMapOf(Integer.class, Object.class));
        verify(mapper).mapForInsert(any(Session.class));
    }

    @Test
    public void itRenewsExistingSession() throws Exception {
        Client client = mock(Client.class);
        SessionMapper mapper = mock(SessionMapper.class);
        SessionRegistryImpl sessionRegistry = new SessionRegistryImpl(client, mapper);
        Date date = new Date();
        VisitorIdentity visitorIdentity = new VisitorIdentity("8e2d80e3-897d-4bc8-99a8-031b9cc55eb9");

        sessionRegistry.renewActiveSession(date, visitorIdentity);

        verify(client).update(anyString(), anyMapOf(Integer.class, Object.class));
        verify(mapper).mapForUpdate(date, visitorIdentity);
    }

    @Test
    public void itFindsExistingSession() throws Exception {
        Client client = mock(Client.class);
        SessionMapper mapper = mock(SessionMapper.class);
        SessionRegistryImpl sessionRegistry = new SessionRegistryImpl(client, mapper);
        Date date = new Date();
        VisitorIdentity visitorIdentity = new VisitorIdentity("8e2d80e3-897d-4bc8-99a8-031b9cc55eb9");

        Map<String, String> expectedSelectResult = new HashMap<>();
        expectedSelectResult.put("id", "8e2d80e3-897d-4bc8-99a8-031b9cc55eb8");
        expectedSelectResult.put("visitorId", "8e2d80e3-897d-4bc8-99a8-031b9cc55eb9");
        expectedSelectResult.put("startAt", "2016-05-26 12:00:00");
        expectedSelectResult.put("endAt", "2016-05-26 12:30:00");

        Map<Integer, Map<String, String>> rows = new HashMap<>();
        rows.put(0, expectedSelectResult);

        when(client.select(anyString(), anyMapOf(Integer.class, Object.class))).thenReturn(rows);

        Session activeSession = sessionRegistry.findActiveSession(date, visitorIdentity);
        assertEquals("8e2d80e3-897d-4bc8-99a8-031b9cc55eb8", activeSession.sessionId.toString());
    }

    @Test(expected = AddException.class)
    public void itThrowsExceptionDuringAddForClientErrors() throws Exception {
        Client client = mock(Client.class);
        SessionMapper mapper = mock(SessionMapper.class);
        SessionRegistryImpl sessionRegistry = new SessionRegistryImpl(client, mapper);
        when(client.insert(anyString(), anyMapOf(Integer.class, Object.class))).thenThrow(new ClientException(null));

        sessionRegistry.addNewSession(mock(Session.class));
    }

    @Test(expected = UpdateException.class)
    public void itThrowsExceptionDuringRenewForClientErrors() throws Exception {
        Client client = mock(Client.class);
        SessionMapper mapper = mock(SessionMapper.class);
        SessionRegistryImpl sessionRegistry = new SessionRegistryImpl(client, mapper);
        when(client.update(anyString(), anyMapOf(Integer.class, Object.class))).thenThrow(new ClientException(null));

        sessionRegistry.renewActiveSession(new Date(), new VisitorIdentity("8e2d80e3-897d-4bc8-99a8-031b9cc55eb9"));
    }

    @Test
    public void itReturnsNullWhenSessionIsNotExistign() throws Exception {
        Client client = mock(Client.class);
        SessionMapper mapper = mock(SessionMapper.class);
        SessionRegistryImpl sessionRegistry = new SessionRegistryImpl(client, mapper);

        Map<Integer, Map<String, String>> rows = new HashMap<>();
        when(client.select(anyString(), anyMapOf(Integer.class, Object.class))).thenReturn(rows);

        Session activeSession = sessionRegistry.findActiveSession(new Date(), new VisitorIdentity("8e2d80e3-897d-4bc8-99a8-031b9cc55eb9"));
        assertEquals(null, activeSession);
    }

    @Test(expected = FindException.class)
    public void itThrowsExceptionDuringClientError() throws Exception {
        Client client = mock(Client.class);
        SessionMapper mapper = mock(SessionMapper.class);
        SessionRegistryImpl sessionRegistry = new SessionRegistryImpl(client, mapper);

        when(client.select(anyString(), anyMapOf(Integer.class, Object.class))).thenThrow(new ClientException(null));

        sessionRegistry.findActiveSession(new Date(), new VisitorIdentity("8e2d80e3-897d-4bc8-99a8-031b9cc55eb9"));
    }
}
