package io.weba.eventor.infrastructure.event.miner;

import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.infrastructure.event.mine.HttpContext;

public class LocalizationMiner implements Miner {
    public void exploit(HttpContext httpContext) throws EventorException {
        httpContext.eventBuilder.localization = httpContext.entry.localization;
    }
}
