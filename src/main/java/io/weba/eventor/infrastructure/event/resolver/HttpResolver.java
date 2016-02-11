package io.weba.eventor.infrastructure.event.resolver;

import io.weba.eventor.domain.event.Event;
import io.weba.eventor.domain.event.resolver.ContextWrapper;
import io.weba.eventor.domain.event.resolver.Resolver;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.domain.exception.resolver.UnexpectedContextException;
import io.weba.eventor.infrastructure.event.enrichment.Enrichment;

public class HttpResolver implements Resolver {

    private Enrichment enrichment;

    public HttpResolver(Enrichment enrichment) {
        this.enrichment = enrichment;
    }

    public Event resolve(ContextWrapper contextWrapper) throws EventorException {
        if (!(contextWrapper.context instanceof HttpContext)) {
            throw new UnexpectedContextException("HttpResolver support " +
                    "io.weba.eventor.infrastructure.eventor.entrypoint.HttpContext<io.weba.eventor.domain.httpLog.HttpLog>."
            );
        }

        HttpContext httpContext = (HttpContext) contextWrapper.context;
        httpContext.eventBuilder.clean();

        this.enrichment.enrich(httpContext);

        return httpContext.eventBuilder.build();
    }
}
