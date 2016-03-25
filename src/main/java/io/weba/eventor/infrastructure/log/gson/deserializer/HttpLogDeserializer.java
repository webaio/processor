package io.weba.eventor.infrastructure.log.gson.deserializer;

import com.google.gson.*;
import io.weba.eventor.domain.http.Request;
import io.weba.eventor.domain.http.Response;
import io.weba.eventor.domain.localization.Localization;
import io.weba.eventor.domain.log.Entry;
import io.weba.eventor.infrastructure.http.parameters.Initializer;

import java.lang.reflect.Type;

public class HttpLogDeserializer implements JsonDeserializer<Entry> {
    private final static String type = "io.weba.eventor.domain.log.Entry";

    public Entry deserialize(JsonElement json, Type typeOf, JsonDeserializationContext context) throws JsonParseException {
        if (!typeOf.getTypeName().equals(HttpLogDeserializer.type)) {
            throw new JsonParseException("HttpLogDeserializer expect type ".concat(HttpLogDeserializer.type));
        }

        JsonObject jsonObject = json.getAsJsonObject();
        Request request = context.deserialize(jsonObject.get("request"), Request.class);

        return new Entry(
                request,
                (Response) context.deserialize(jsonObject.get("response"), Response.class),
                (Localization) context.deserialize(jsonObject.get("localization"), Localization.class),
                Initializer.initializeFromRequest(request)
        );
    }
}
