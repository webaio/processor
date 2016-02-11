package io.weba.eventor.domain.exception.device;

import io.weba.eventor.domain.exception.DropEventException;

public class UnknownFeatureException extends DropEventException {
    public UnknownFeatureException(String type) {
        super("Unknown feature " + type);
    }
}
