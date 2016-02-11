package io.weba.eventor.infrastructure.event.enrichment;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class EnrichmentChainBuilder {
    private LinkedHashSet<Enrichment> chain = new LinkedHashSet<Enrichment>();

    @SuppressWarnings({"unchecked"})
    public EnrichmentChain buildChain() {
        EnrichmentChain processorChain = new EnrichmentChain((HashSet<Enrichment>) chain.clone());

        clear();

        return processorChain;
    }

    public void addEnrichment(Enrichment enrichment) {
        chain.add(enrichment);
    }

    public void clear() {
        chain.clear();
    }
}
