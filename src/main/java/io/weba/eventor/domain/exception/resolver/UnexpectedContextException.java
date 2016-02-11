package io.weba.eventor.domain.exception.resolver;

import io.weba.eventor.domain.exception.EventorException;

public class UnexpectedContextException extends EventorException {
    public UnexpectedContextException(String message) {
        super(message);
    }
}
