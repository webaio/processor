package io.weba.eventor.domain.http.voter;

import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.domain.log.Entry;

import java.util.Set;

public class VoterChain implements Voter {
    private Set<Voter> chain;

    public VoterChain(Set<Voter> chain) {
        this.chain = chain;
    }

    @Override
    public void vote(Entry entry) throws EventorException {
        for (Voter voter : chain) {
            voter.vote(entry);
        }
    }
}
