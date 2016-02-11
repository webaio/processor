package io.weba.eventor.domain.log;

import io.weba.eventor.domain.exception.EventorException;

public interface Reader {
    HttpLog read(String log) throws EventorException;
}