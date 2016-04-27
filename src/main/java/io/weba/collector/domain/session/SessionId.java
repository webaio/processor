package io.weba.collector.domain.session;

import io.weba.collector.domain.common.UuidIdentifier;

import java.util.UUID;

public class SessionId extends UuidIdentifier {
    public SessionId(UUID uuid) {
        super(uuid);
    }
}
