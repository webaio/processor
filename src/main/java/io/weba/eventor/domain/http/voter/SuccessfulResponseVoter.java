package io.weba.eventor.domain.http.voter;

import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.domain.log.Entry;

public class SuccessfulResponseVoter implements Voter {
    @Override
    public void vote(Entry entry) throws EventorException {
        if(!entry.response.isSuccessfulStatusCode()) {
            throw new EventorException("Eventor is able to process only successful responses.");
        }
    }
}
