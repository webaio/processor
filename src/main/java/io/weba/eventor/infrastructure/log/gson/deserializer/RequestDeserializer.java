package io.weba.eventor.infrastructure.log.gson.deserializer;

import com.google.common.net.InetAddresses;
import com.google.gson.*;
import io.weba.eventor.domain.http.Headers;
import io.weba.eventor.domain.http.Method;
import io.weba.eventor.domain.http.Request;
import org.joda.time.DateTime;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

public class RequestDeserializer implements JsonDeserializer<Request> {
    private final static String type = "io.weba.eventor.domain.http.Request";

    public Request deserialize(JsonElement json, Type typeOf, JsonDeserializationContext context) throws JsonParseException {
        if (!typeOf.getTypeName().equals(RequestDeserializer.type)) {
            throw new JsonParseException("RequestDeserializer expect type ".concat(RequestDeserializer.type));
        }

        JsonObject jsonObject = json.getAsJsonObject();

        return new Request(
                jsonObject.get("id").getAsString(),
                (Headers) context.deserialize(jsonObject.get("headers"), Headers.class),
                new String(Base64.getDecoder().decode(jsonObject.get("content").getAsString())),
                getUri(jsonObject),
                new DateTime((String) jsonObject.get("date").getAsString()).toDate(),
                Method.recognizeHttpMethod(jsonObject.get("method").getAsString()),
                getIP(jsonObject)
        );
    }

    private String getIP(JsonObject jsonObject) {
        String ip = (String) jsonObject.get("x_forwarded_for").getAsString();
        if (!InetAddresses.isInetAddress(ip)) {
            ip = (String) jsonObject.get("remote_addr").getAsString();
        }
        return ip;
    }

    private URI getUri(JsonObject jsonObject) {
        URI uri;
        try {
            uri = new URI((String) jsonObject.get("uri").getAsString());
        } catch (URISyntaxException e) {
            throw new JsonParseException("Cannot parse request uri to URI object.");
        }
        return uri;
    }
}
