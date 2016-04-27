package io.weba.collector.application.factory.memsql;

import io.weba.collector.application.collector.Collector;
import io.weba.collector.application.collector.CollectorException;
import io.weba.collector.application.serializer.Serializer;
import io.weba.collector.application.storage.transactional.Client;
import io.weba.collector.infrastructure.application.collector.CollectorImpl;
import io.weba.collector.infrastructure.application.serializer.GsonSerializerImpl;
import io.weba.collector.infrastructure.application.storage.memsql.ClientImpl;

import java.sql.SQLException;

public class CollectorFactory {
    public Collector create(String host, Integer port, String databaseName, String user, String password) throws CollectorException {
        try {
            Client client = new ClientImpl(host, port, databaseName, user, password);
            Serializer serializer = new GsonSerializerImpl();

            return new CollectorImpl(
                    client,
                    new SessionRegistryFactory().create(client),
                    new EventSessionRegistryFactory().create(client, serializer),
                    new EventAggregatorRegistryFactory().create(client)
            );

        } catch (SQLException e) {
            throw new CollectorException(e);
        }
    }
}
