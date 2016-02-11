package io.weba.eventor.infrastructure.event.enrichment;

import io.weba.eventor.domain.event.Type;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.infrastructure.event.resolver.HttpContext;

public class EventTypeEnrichment implements Enrichment {
    public void enrich(HttpContext httpContext) throws EventorException {
        httpContext.eventBuilder.type = Type.recognizeType(httpContext.httpLog.parameters.getEventType());
    }
}
