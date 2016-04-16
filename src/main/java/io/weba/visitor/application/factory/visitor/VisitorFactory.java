package io.weba.visitor.application.factory.visitor;

import io.weba.visitor.application.factory.storage.elasticsearch.ElasticsearchRegistryFactory;
import io.weba.visitor.application.factory.storage.redis.RedisRegistryFactory;
import io.weba.visitor.application.storage.exception.MapperException;
import io.weba.visitor.application.visitor.Visitor;

import java.net.UnknownHostException;

public class VisitorFactory {
    public Visitor create(String redisHost, Integer redisPort, String elasticsearchHost, Integer elasticsearchPort) throws VisitorFactoryException {
        try {
            ElasticsearchRegistryFactory elasticsearchRegistryFactory = new ElasticsearchRegistryFactory(elasticsearchHost, elasticsearchPort);
            RedisRegistryFactory redisVisitRegistryFactory = new RedisRegistryFactory();

            return new Visitor(
                    redisVisitRegistryFactory.create(redisHost, redisPort),
                    elasticsearchRegistryFactory.createVisitRegistry(),
                    elasticsearchRegistryFactory.createActionRegistry()
            );
        } catch (UnknownHostException | MapperException e) {
            throw new VisitorFactoryException(e);
        }
    }
}
