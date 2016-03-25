package io.weba.eventor.infrastructure.event.payload;

import io.weba.eventor.domain.event.Type;
import io.weba.eventor.domain.event.payload.PayloadFactory;
import io.weba.eventor.domain.event.payload.FactoryGuesser;
import io.weba.eventor.domain.exception.EventorException;

import java.util.Map;

public class MapFactoryGuesser implements FactoryGuesser {
    private Map<Type, PayloadFactory> map;

    public MapFactoryGuesser(Map<Type, PayloadFactory> map) {
        this.map = map;
    }

    public PayloadFactory guessFactory(Type type) throws EventorException {
        if (map.containsKey(type)) {
            return map.get(type);
        }

        throw new EventorException("Cannot find appropriate factory for ".concat(type.toString()));
    }
}
