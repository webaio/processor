package io.weba.eventor.application.http.voter;

import io.weba.eventor.domain.http.voter.Factory;
import io.weba.eventor.domain.http.voter.SuccessfulResponseVoter;
import io.weba.eventor.domain.http.voter.Voter;
import io.weba.eventor.domain.http.voter.VoterChain;

import java.util.HashSet;
import java.util.Set;

public class VoterFactory implements Factory {
    @Override
    public Voter create() {
        Set<Voter> voterSet = new HashSet<Voter>();
        voterSet.add(new SuccessfulResponseVoter());

        return new VoterChain(voterSet);
    }
}
