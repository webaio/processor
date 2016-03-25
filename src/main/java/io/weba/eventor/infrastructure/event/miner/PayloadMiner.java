package io.weba.eventor.infrastructure.event.miner;

import io.weba.eventor.domain.event.payload.PayloadFactory;
import io.weba.eventor.domain.event.payload.FactoryGuesser;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.infrastructure.event.mine.HttpContext;

public class PayloadMiner implements Miner {
    FactoryGuesser factoryGuesser;

    public PayloadMiner(FactoryGuesser factoryGuesser) {
        this.factoryGuesser = factoryGuesser;
    }

    public void exploit(HttpContext httpContext) throws EventorException {
        PayloadFactory payloadFactory = this.factoryGuesser.guessFactory(httpContext.eventBuilder.type);
        httpContext.eventBuilder.payload = payloadFactory.createPayload(httpContext);
    }
}
