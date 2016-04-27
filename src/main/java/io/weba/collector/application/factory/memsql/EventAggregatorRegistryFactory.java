package io.weba.collector.application.factory.memsql;

import io.weba.collector.application.storage.transactional.Client;
import io.weba.collector.domain.eventaggregator.EventAggregatorRegistry;
import io.weba.collector.infrastructure.domain.memsql.eventaggregator.EventAggregatorMapperImpl;
import io.weba.collector.infrastructure.domain.memsql.eventaggregator.EventAggregatorRegistryImpl;

public class EventAggregatorRegistryFactory {
    public EventAggregatorRegistry create(Client client) {
        return new EventAggregatorRegistryImpl(client, new EventAggregatorMapperImpl());
    }
}
