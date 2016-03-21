package io.weba.eventor.infrastructure.event.miner;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class MinersChainBuilder {
    private LinkedHashSet<Miner> chain = new LinkedHashSet<Miner>();

    @SuppressWarnings({"unchecked"})
    public MinerChain buildChain() {
        MinerChain processorChain = new MinerChain((HashSet<Miner>) chain.clone());

        clear();

        return processorChain;
    }

    public void addEnrichment(Miner miner) {
        chain.add(miner);
    }

    public void clear() {
        chain.clear();
    }
}
