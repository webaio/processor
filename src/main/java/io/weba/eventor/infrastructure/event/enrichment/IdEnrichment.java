package io.weba.eventor.infrastructure.event.enrichment;

import io.weba.eventor.domain.event.ID;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.infrastructure.event.mine.HttpContext;

public class IdEnrichment implements Enrichment {
    public void enrich(HttpContext httpContext) throws EventorException {
        httpContext.eventBuilder.id = new ID(httpContext.entry.request.id);
    }
}
