package io.weba.eventor.domain.http;

public enum Method {
    OPTIONS("OPTIONS"),
    HEAD("HEAD"),
    PUT("PUT"),
    DELETE("DELETE"),
    TRACE("TRACE"),
    CONNECT("CONNECT"),
    GET("GET"),
    POST("POST");

    private String type;

    public String toString() {
        return this.type;
    }

    private Method(String type) {
        this.type = type;
    }

    public static Method recognizeHttpMethod(String method) {
        try {
            return Method.valueOf(method.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Method.GET;
        }
    }
}
