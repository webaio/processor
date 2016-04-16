package io.weba.visitor.application.storage;

import io.weba.visitor.application.storage.exception.MapperException;

import java.util.HashMap;
import java.util.Map;

public class ClassMapper {
    private final Map<String, Mapper> mapperStorage;

    public ClassMapper() {
        this.mapperStorage = new HashMap<>();
    }

    public void addMapper(String aggregateClassName, Mapper mapper) throws MapperException {
        if (this.mapperStorage.containsKey(aggregateClassName)) {
            throw new MapperException(String.format("The %s mapper already registered", aggregateClassName));
        }

        this.mapperStorage.put(aggregateClassName, mapper);
    }

    public Mapper findMapper(String aggregateClassName) throws MapperException {
        if (this.mapperStorage.containsKey(aggregateClassName)) {
            return this.mapperStorage.get(aggregateClassName);
        }

        throw new MapperException(String.format("Could not find %s mapper", aggregateClassName));
    }
}
