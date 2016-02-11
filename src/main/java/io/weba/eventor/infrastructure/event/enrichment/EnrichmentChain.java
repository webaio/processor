package io.weba.eventor.infrastructure.event.enrichment;

import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.infrastructure.event.mine.HttpContext;

import java.util.Set;

public class EnrichmentChain implements Enrichment {
    private Set<Enrichment> chain;

    public EnrichmentChain(Set<Enrichment> chain) {
        this.chain = chain;
    }

    public void enrich(HttpContext httpContext) throws EventorException {
        for (Enrichment enrichment : chain) {
            enrichment.enrich(httpContext);
        }
    }
}
