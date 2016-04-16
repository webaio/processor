package io.weba.visitor.infrastructure.domain.action;

import io.weba.visitor.application.storage.elasticsearch.ElasticsearchEntityManager;
import io.weba.visitor.domain.action.Action;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ElasticsearchActionRegistryTest {
    @Test
    public void itAddsActionToStorage() {
        ElasticsearchEntityManager elasticsearchEntityManager = mock(ElasticsearchEntityManager.class);
        Action action = mock(Action.class);
        ElasticsearchActionRegistry elasticsearchActionRegistry = new ElasticsearchActionRegistry(elasticsearchEntityManager);
        elasticsearchActionRegistry.add(action);

        verify(elasticsearchEntityManager, atLeastOnce()).persist(action);
    }
}
