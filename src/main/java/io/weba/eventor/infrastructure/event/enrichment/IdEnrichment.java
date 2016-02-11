package io.weba.eventor.infrastructure.event.enrichment;

import io.weba.eventor.domain.event.Id;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.infrastructure.event.resolver.HttpContext;

public class IdEnrichment implements Enrichment {
    public void enrich(HttpContext httpContext) throws EventorException {
        httpContext.eventBuilder.id = new Id(httpContext.httpLog.request.id);
    }
}
