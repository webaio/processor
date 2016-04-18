package io.weba.visitor.domain.visit;

import io.weba.visitor.domain.common.UuidIdentifier;

import java.util.UUID;

final public class VisitId extends UuidIdentifier {
    public VisitId(UUID uuid) {
        super(uuid);
    }
}
