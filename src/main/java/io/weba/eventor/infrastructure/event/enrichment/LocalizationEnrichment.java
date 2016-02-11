package io.weba.eventor.infrastructure.event.enrichment;

import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.infrastructure.event.resolver.HttpContext;

public class LocalizationEnrichment implements Enrichment {
    public void enrich(HttpContext httpContext) throws EventorException {
        httpContext.eventBuilder.localization = httpContext.httpLog.localization;
    }
}
