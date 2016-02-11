package io.weba.eventor.infrastructure.log.gson.deserializer;

import com.google.gson.*;
import io.weba.eventor.domain.http.Headers;
import io.weba.eventor.domain.http.Response;

import java.lang.reflect.Type;

public class ResponseDeserializer implements JsonDeserializer<Response> {
    private final static String type = "io.weba.eventor.domain.http.Response";

    public Response deserialize(JsonElement json, Type typeOf, JsonDeserializationContext context) throws JsonParseException {
        if (!typeOf.getTypeName().equals(ResponseDeserializer.type)) {
            throw new JsonParseException("ResponseDeserializer expect type ".concat(ResponseDeserializer.type));
        }

        JsonObject jsonObject = json.getAsJsonObject();

        return new Response(
                Integer.parseInt(jsonObject.get("status").getAsString()),
                (Headers) context.deserialize(jsonObject.get("headers"), Headers.class)
        );
    }
}
