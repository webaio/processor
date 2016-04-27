package io.weba.collector.application.factory.memsql;

import io.weba.collector.application.storage.transactional.Client;
import io.weba.collector.domain.session.SessionRegistry;
import io.weba.collector.infrastructure.domain.memsql.session.SessionMapperImpl;
import io.weba.collector.infrastructure.domain.memsql.session.SessionRegistryImpl;

public class SessionRegistryFactory {
    public SessionRegistry create(Client client) {
        return new SessionRegistryImpl(client, new SessionMapperImpl());
    }
}
