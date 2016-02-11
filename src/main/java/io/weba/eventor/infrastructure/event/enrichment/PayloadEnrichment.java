package io.weba.eventor.infrastructure.event.enrichment;

import io.weba.eventor.domain.event.payload.Factory;
import io.weba.eventor.domain.event.payload.FactoryGuesser;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.infrastructure.event.resolver.HttpContext;

public class PayloadEnrichment implements Enrichment {

    FactoryGuesser factoryGuesser;

    public PayloadEnrichment(FactoryGuesser factoryGuesser) {
        this.factoryGuesser = factoryGuesser;
    }

    public void enrich(HttpContext httpContext) throws EventorException {
        Factory factory = this.factoryGuesser.guessFactory(httpContext.eventBuilder.type);
        httpContext.eventBuilder.payload = factory.createPayload(httpContext);
    }
}
