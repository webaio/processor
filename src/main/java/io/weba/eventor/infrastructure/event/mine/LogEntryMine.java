package io.weba.eventor.infrastructure.event.mine;

import io.weba.eventor.domain.event.Event;
import io.weba.eventor.domain.event.mine.ContextWrapper;
import io.weba.eventor.domain.event.mine.Mine;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.domain.exception.resolver.UnexpectedContextException;
import io.weba.eventor.infrastructure.event.miner.Miner;

public class LogEntryMine implements Mine {
    private Miner miner;

    public LogEntryMine(Miner miner) {
        this.miner = miner;
    }

    public Event exploit(ContextWrapper contextWrapper) throws EventorException {
        if (!(contextWrapper.context instanceof HttpContext)) {
            throw new UnexpectedContextException("LogEntryMine support " +
                    "io.weba.eventor.infrastructure.eventor.entrypoint.HttpContext<io.weba.eventor.domain.entry.Entry>."
            );
        }

        HttpContext httpContext = (HttpContext) contextWrapper.context;
        httpContext.eventBuilder.clean();

        this.miner.enrich(httpContext);

        return httpContext.eventBuilder.build();
    }
}
