package io.weba.visitor.infrastructure.application.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.weba.visitor.application.serializer.Serializer;

import java.util.Date;

public class GsonSerializerImpl implements Serializer {
    private final Gson gson;

    public GsonSerializerImpl() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
        this.gson = gsonBuilder.create();
    }

    public String serialize(Object objectToSerialize) {
        return this.gson.toJson(objectToSerialize);
    }

    public Object deserialize(String object, Class type) {
        return this.gson.fromJson(object, type);
    }
}
