package io.weba.eventor.domain.event.payload;

import io.weba.eventor.domain.event.Type;
import io.weba.eventor.domain.exception.EventorException;

public interface FactoryGuesser {
    PayloadFactory guessFactory(Type type) throws EventorException;
}
