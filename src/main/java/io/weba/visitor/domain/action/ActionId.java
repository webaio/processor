package io.weba.visitor.domain.action;

import io.weba.visitor.domain.common.UuidIdentifier;

import java.util.UUID;

public class ActionId extends UuidIdentifier {
    public ActionId(UUID uuid) {
        super(uuid);
    }
}
