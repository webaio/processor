package io.weba.eventor.infrastructure.event.enrichment;

import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.infrastructure.event.mine.HttpContext;

public interface Enrichment {
    void enrich(HttpContext httpContext) throws EventorException;
}
