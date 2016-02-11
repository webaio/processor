package io.weba.eventor.domain.event.resolver;

import io.weba.eventor.domain.event.Event;
import io.weba.eventor.domain.exception.EventorException;

public interface Resolver {
    Event resolve(ContextWrapper contextWrapper) throws EventorException;
}
