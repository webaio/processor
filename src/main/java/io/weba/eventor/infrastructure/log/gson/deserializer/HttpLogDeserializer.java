package io.weba.eventor.infrastructure.log.gson.deserializer;

import com.google.gson.*;
import io.weba.eventor.domain.http.Request;
import io.weba.eventor.domain.http.Response;
import io.weba.eventor.domain.localization.Localization;
import io.weba.eventor.domain.log.HttpLog;

import java.lang.reflect.Type;

public class HttpLogDeserializer implements JsonDeserializer<HttpLog> {
    private final static String type = "io.weba.eventor.domain.log.HttpLog";

    public HttpLog deserialize(JsonElement json, Type typeOf, JsonDeserializationContext context) throws JsonParseException {
        if (!typeOf.getTypeName().equals(HttpLogDeserializer.type)) {
            throw new JsonParseException("HttpLogDeserializer expect type ".concat(HttpLogDeserializer.type));
        }

        JsonObject jsonObject = json.getAsJsonObject();

        return new HttpLog(
                (Request) context.deserialize(jsonObject.get("request"), Request.class),
                (Response) context.deserialize(jsonObject.get("response"), Response.class),
                (Localization) context.deserialize(jsonObject.get("localization"), Localization.class)
        );
    }
}
