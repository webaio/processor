package io.weba.eventor.domain.eventor;

import io.weba.eventor.domain.exception.EventorException;

import java.io.IOException;

public interface EventorFactory {
    Eventor create() throws IOException, EventorException;
}
