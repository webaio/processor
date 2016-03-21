package io.weba.eventor.infrastructure.event.miner;

import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.infrastructure.event.mine.HttpContext;

import java.util.Set;

public class MinerChain implements Miner {
    private Set<Miner> chain;

    public MinerChain(Set<Miner> chain) {
        this.chain = chain;
    }

    public void enrich(HttpContext httpContext) throws EventorException {
        for (Miner miner : chain) {
            miner.enrich(httpContext);
        }
    }
}
