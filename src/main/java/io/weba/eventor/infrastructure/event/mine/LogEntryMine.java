package io.weba.eventor.infrastructure.event.mine;

import io.weba.eventor.domain.event.Event;
import io.weba.eventor.domain.event.mine.ContextWrapper;
import io.weba.eventor.domain.event.mine.Mine;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.domain.exception.resolver.UnexpectedContextException;
import io.weba.eventor.infrastructure.event.enrichment.Enrichment;

public class LogEntryMine implements Mine {
    private Enrichment enrichment;

    public LogEntryMine(Enrichment enrichment) {
        this.enrichment = enrichment;
    }

    public Event exploit(ContextWrapper contextWrapper) throws EventorException {
        if (!(contextWrapper.context instanceof HttpContext)) {
            throw new UnexpectedContextException("LogEntryMine support " +
                    "io.weba.eventor.infrastructure.eventor.entrypoint.HttpContext<io.weba.eventor.domain.entry.Entry>."
            );
        }

        HttpContext httpContext = (HttpContext) contextWrapper.context;
        httpContext.eventBuilder.clean();

        this.enrichment.enrich(httpContext);

        return httpContext.eventBuilder.build();
    }
}
