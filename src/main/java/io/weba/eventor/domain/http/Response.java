package io.weba.eventor.domain.http;

public class Response {
    public final int statusCode;
    public final Headers headers;

    public Response(int statusCode, Headers headers) {
        this.statusCode = statusCode;
        this.headers = headers;
    }

    public boolean isSuccessfulStatusCode() {
        return statusCode == 200 || statusCode == 204;
    }
}
