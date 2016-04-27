package io.weba.collector.infrastructure.domain.memsql.eventaggregator;

import io.weba.eventor.domain.event.VisitorIdentity;
import io.weba.collector.application.storage.transactional.Client;
import io.weba.collector.application.storage.transactional.exception.ClientException;
import io.weba.collector.domain.eventaggregator.EventAggregator;
import io.weba.collector.domain.eventaggregator.EventAggregatorMapper;
import io.weba.collector.domain.eventaggregator.EventAggregatorRegistry;
import io.weba.collector.domain.eventaggregator.exceptions.AddException;
import io.weba.collector.domain.eventaggregator.exceptions.UpdateException;
import io.weba.collector.domain.session.SessionId;

public class EventAggregatorRegistryImpl implements EventAggregatorRegistry {
    private final Client client;
    private final EventAggregatorMapper eventAggregatorMapper;

    public EventAggregatorRegistryImpl(Client client, EventAggregatorMapper eventAggregatorMapper) {
        this.client = client;
        this.eventAggregatorMapper = eventAggregatorMapper;
    }

    @Override
    public Integer add(EventAggregator eventAggregator) throws AddException {
        try {
            String sql = "INSERT INTO event_aggregator(id, visitorId, sessionId, numberOfActions) VALUES (?, ?, ?, 1)";

            return this.client.insert(sql, this.eventAggregatorMapper.mapForInsert(eventAggregator));
        } catch (ClientException e) {
            throw new AddException(e);
        }
    }

    @Override
    public Integer update(SessionId sessionId, VisitorIdentity visitorId) throws UpdateException {
        try {
            String sql = "UPDATE event_aggregator SET numberOfActions = numberOfActions + 1 WHERE sessionId = ? AND visitorId = ?";

            return this.client.update(sql, this.eventAggregatorMapper.mapForUpdate(sessionId, visitorId));
        } catch (ClientException e) {
            throw new UpdateException(e);
        }
    }
}
