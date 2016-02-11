package io.weba.eventor.domain.http;

import java.util.Map;

public class Headers {

    public final Map<String, Header> bag;

    public Headers(Map<String, Header> headers) {
        this.bag = headers;
    }
}
