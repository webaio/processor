package io.weba.visitor.application.serializer;

public interface Serializer {
    String serialize(Object object);

    Object deserialize(String object, Class type);
}
