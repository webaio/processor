package io.weba.eventor.domain.eventor;

import io.weba.eventor.domain.event.Event;
import io.weba.eventor.domain.exception.EventorException;

public interface Eventor {
    Event exploitFromLog(String log) throws EventorException;
}
