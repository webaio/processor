package io.weba.eventor.domain.event.mine;

import io.weba.eventor.domain.exception.EventorException;

import java.io.IOException;

public interface MineFactory {
    Mine create() throws EventorException, IOException;
}