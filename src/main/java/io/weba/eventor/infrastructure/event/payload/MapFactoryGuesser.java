package io.weba.eventor.infrastructure.event.payload;

import io.weba.eventor.domain.event.Type;
import io.weba.eventor.domain.event.payload.Factory;
import io.weba.eventor.domain.event.payload.FactoryGuesser;
import io.weba.eventor.domain.exception.EventorException;

import java.util.Map;

public class MapFactoryGuesser implements FactoryGuesser {
    private Map<Type, Factory> map;

    public MapFactoryGuesser(Map<Type, Factory> map) {
        this.map = map;
    }

    public Factory guessFactory(Type type) throws EventorException {
        if (map.containsKey(type)) {
            return (Factory) map.get(type);
        }

        throw new EventorException("Cannot find appropriate factory for ".concat(type.toString()));
    }
}
