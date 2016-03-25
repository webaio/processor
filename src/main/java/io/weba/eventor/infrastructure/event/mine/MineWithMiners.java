package io.weba.eventor.infrastructure.event.mine;

import io.weba.eventor.domain.event.Event;
import io.weba.eventor.domain.event.mine.ContextWrapper;
import io.weba.eventor.domain.event.mine.Mine;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.domain.exception.resolver.UnexpectedContextException;
import io.weba.eventor.infrastructure.event.miner.Miner;

public class MineWithMiners implements Mine {
    private Miner miner;

    public MineWithMiners(Miner miner) {
        this.miner = miner;
    }

    public Event exploit(ContextWrapper contextWrapper) throws EventorException {
        if (!(contextWrapper.context instanceof HttpContext)) {
            throw new UnexpectedContextException("Only io.weba.eventor.domain.event.mine.ContextWrapper is supported.");
        }

        HttpContext httpContext = (HttpContext) contextWrapper.context;
        httpContext.eventBuilder.clean();

        this.miner.exploit(httpContext);

        return httpContext.eventBuilder.build();
    }
}
