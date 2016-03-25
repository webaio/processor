package io.weba.eventor.domain.event.payload;

import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.infrastructure.event.mine.HttpContext;

public interface PayloadFactory {
    Payload createPayload(HttpContext httpContext) throws EventorException;
}
