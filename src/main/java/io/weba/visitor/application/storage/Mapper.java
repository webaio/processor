package io.weba.visitor.application.storage;

import java.util.Map;

public interface Mapper<T> {
    Map<String, String> mapFields(T object);

    T reconstituteFromData(Map<String, String> data);

    String[] getFields();

    String getIncrementField();

    Long getExpiresAt(T object);

    String getIdentifier(T object);
}
