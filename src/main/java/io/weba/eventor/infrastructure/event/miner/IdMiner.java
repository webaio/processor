package io.weba.eventor.infrastructure.event.miner;

import io.weba.eventor.domain.event.ID;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.infrastructure.event.mine.HttpContext;

public class IdMiner implements Miner {
    public void exploit(HttpContext httpContext) throws EventorException {
        httpContext.eventBuilder.id = new ID(httpContext.entry.request.id);
    }
}
