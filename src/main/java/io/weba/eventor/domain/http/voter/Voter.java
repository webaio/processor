package io.weba.eventor.domain.http.voter;

import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.domain.log.Entry;

public interface Voter {
    void vote(Entry entry) throws EventorException;
}
