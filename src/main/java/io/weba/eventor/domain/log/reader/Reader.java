package io.weba.eventor.domain.log.reader;

import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.domain.log.Entry;

public interface Reader {
    Entry read(String log) throws EventorException;
}