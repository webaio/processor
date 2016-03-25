package io.weba.eventor.infrastructure.event.miner;

import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.infrastructure.event.mine.HttpContext;

public interface Miner {
    void exploit(HttpContext httpContext) throws EventorException;
}
