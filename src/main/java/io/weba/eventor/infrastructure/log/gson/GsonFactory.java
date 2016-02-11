package io.weba.eventor.infrastructure.log.gson;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.weba.eventor.domain.http.Headers;
import io.weba.eventor.domain.http.Request;
import io.weba.eventor.domain.http.Response;
import io.weba.eventor.domain.log.HttpLog;
import io.weba.eventor.infrastructure.log.gson.deserializer.HeadersDeserializer;
import io.weba.eventor.infrastructure.log.gson.deserializer.HttpLogDeserializer;
import io.weba.eventor.infrastructure.log.gson.deserializer.RequestDeserializer;
import io.weba.eventor.infrastructure.log.gson.deserializer.ResponseDeserializer;

public class GsonFactory {

    public static Gson create() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(HttpLog.class, new HttpLogDeserializer());
        gsonBuilder.registerTypeAdapter(Request.class, new RequestDeserializer());
        gsonBuilder.registerTypeAdapter(Response.class, new ResponseDeserializer());
        gsonBuilder.registerTypeAdapter(Headers.class, new HeadersDeserializer());
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

        return gsonBuilder.create();
    }
}
