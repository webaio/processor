package io.weba.eventor.domain.log;

import io.weba.eventor.domain.http.Request;
import io.weba.eventor.domain.http.Response;
import io.weba.eventor.domain.http.parameters.Bag;
import io.weba.eventor.domain.localization.Localization;

import java.util.Objects;

public class Entry {
    public final Request request;
    public final Response response;
    public final Localization localization;
    public final Bag parameters;

    public Entry(Request request, Response response, Localization localization, Bag parameters) {
        this.request = Objects.requireNonNull(request);
        this.response = Objects.requireNonNull(response);
        this.localization = Objects.requireNonNull(localization);
        this.parameters = Objects.requireNonNull(parameters);
    }
}
