package io.weba.collector.domain.eventaggregator;

import io.weba.collector.domain.common.UuidIdentifier;

import java.util.UUID;

public class EventAggregatorId extends UuidIdentifier {
    public EventAggregatorId(UUID uuid) {
        super(uuid);
    }
}
