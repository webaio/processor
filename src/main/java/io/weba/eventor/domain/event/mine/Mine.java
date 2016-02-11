package io.weba.eventor.domain.event.mine;

import io.weba.eventor.domain.event.Event;
import io.weba.eventor.domain.exception.EventorException;

public interface Mine {
    Event exploit(ContextWrapper contextWrapper) throws EventorException;
}
