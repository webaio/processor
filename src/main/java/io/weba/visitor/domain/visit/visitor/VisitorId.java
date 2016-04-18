package io.weba.visitor.domain.visit.visitor;

import io.weba.visitor.domain.common.UuidIdentifier;

import java.util.UUID;

final public class VisitorId extends UuidIdentifier {
    public VisitorId(UUID uuid) {
        super(uuid);
    }
}
