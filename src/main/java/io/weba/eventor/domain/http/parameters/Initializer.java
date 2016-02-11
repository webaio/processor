package io.weba.eventor.domain.http.parameters;

import io.weba.eventor.domain.http.Request;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class Initializer {
    public static Bag initializeFromRequest(Request request) {
        UriComponents components = UriComponentsBuilder.fromUri(request.uri).build();

        return new MapBag(components.getQueryParams().toSingleValueMap());
    }
}
