package io.weba.eventor.domain.http;

import java.util.Map;

public class Headers {
    public final Map<String, Header> bag;

    public Headers(Map<String, Header> headers) {
        this.bag = headers;
    }

    public String getUserAgent() {
        String userAgent = "";
        if(bag.containsKey("user-agent")) {
            userAgent = bag.get("user-agent").value;
        }

        return userAgent;
    }
}
