package io.weba.eventor.infrastructure.log.gson.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import io.weba.eventor.domain.http.Header;
import io.weba.eventor.domain.http.Headers;

import java.lang.reflect.Type;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;

public class HeadersDeserializer implements JsonDeserializer<Headers> {
    private final static String type = "io.weba.eventor.domain.http.Headers";

    public Headers deserialize(JsonElement json, Type typeOf, JsonDeserializationContext context) throws JsonParseException {
        if (!typeOf.getTypeName().equals(HeadersDeserializer.type)) {
            throw new JsonParseException("HeadersDeserializer expect type ".concat(HeadersDeserializer.type));
        }

        HashMap<String, Header> headersMap = new HashMap<String, Header>();
        byte[] headersBytes = Base64.getDecoder().decode(json.getAsString());
        for (String headerName : new String(headersBytes).split("\\r?\\n")) {
            Integer indexOf = headerName.indexOf(":");
            if (indexOf.equals(-1)) {
                continue;
            }

            Header header = new Header(
                    headerName.substring(0, indexOf),
                    headerName.substring(indexOf + 1, headerName.length())
            );

            headersMap.put(header.key, header);
        }

        return new Headers(Collections.unmodifiableMap((HashMap<String, Header>) headersMap));
    }
}
