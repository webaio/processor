package io.weba.collector.domain.eventsession;

import io.weba.collector.domain.common.UuidIdentifier;

import java.util.UUID;

public class EventSessionId extends UuidIdentifier {
    public EventSessionId(UUID uuid) {
        super(uuid);
    }
}
