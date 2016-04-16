package io.weba.visitor.application.storage;

import io.weba.visitor.domain.common.AggregateRoot;

import java.util.Map;

public class ChangeSet {
    public final AggregateRoot aggregateRoot;
    public final Map<String, String> dataToUpdate;

    public ChangeSet(AggregateRoot aggregateRoot, Map<String, String> dataToUpdate) {
        this.aggregateRoot = aggregateRoot;
        this.dataToUpdate = dataToUpdate;
    }
}
