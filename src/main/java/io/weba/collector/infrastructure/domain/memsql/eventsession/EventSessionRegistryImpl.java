package io.weba.collector.infrastructure.domain.memsql.eventsession;

import io.weba.collector.application.storage.transactional.Client;
import io.weba.collector.application.storage.transactional.exception.ClientException;
import io.weba.collector.domain.eventsession.EventSessionMapper;
import io.weba.collector.domain.eventsession.EventSessionRegistry;
import io.weba.collector.domain.eventsession.EventSession;
import io.weba.collector.domain.eventsession.exception.AddException;

public class EventSessionRegistryImpl implements EventSessionRegistry {
    private final Client client;
    private final EventSessionMapper eventSessionMapper;

    public EventSessionRegistryImpl(Client client, EventSessionMapper eventSessionMapper) {
        this.client = client;
        this.eventSessionMapper = eventSessionMapper;
    }

    @Override
    public Integer addEvent(EventSession eventSession) throws AddException {
        try {
            String sql = "INSERT INTO event_session(id, eventId, sessionId, visitorId, visitAt, eventType, payload, device, localization, dates) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            return this.client.insert(sql, this.eventSessionMapper.mapForInsert(eventSession));
        } catch (ClientException e) {
            throw new AddException(e);
        }
    }
}
