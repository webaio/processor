package io.weba.eventor.domain.http;

import java.net.URI;
import java.util.Date;

public class Request {
    public final String id;
    public final Headers headers;
    public final String content;
    public final Date date;
    public final Method method;
    public final String IP;
    public final URI uri;

    public Request(String id, Headers headers, String content, URI uri, Date date, Method method, String IP) {
        this.id = id;
        this.headers = headers;
        this.content = content;
        this.uri = uri;
        this.date = date;
        this.method = method;
        this.IP = IP;
    }
}
