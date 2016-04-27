package io.weba.collector.application.factory.memsql;

import io.weba.collector.application.serializer.Serializer;
import io.weba.collector.application.storage.transactional.Client;
import io.weba.collector.domain.eventsession.EventSessionRegistry;
import io.weba.collector.infrastructure.domain.memsql.eventsession.EventSessionMapperImpl;
import io.weba.collector.infrastructure.domain.memsql.eventsession.EventSessionRegistryImpl;

public class EventSessionRegistryFactory {
    public EventSessionRegistry create(Client client, Serializer serializer) {
        return new EventSessionRegistryImpl(client, new EventSessionMapperImpl(serializer));
    }
}
