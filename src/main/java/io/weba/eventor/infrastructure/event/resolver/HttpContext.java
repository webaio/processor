package io.weba.eventor.infrastructure.event.resolver;

import io.weba.eventor.domain.event.EventBuilder;
import io.weba.eventor.domain.log.HttpLog;

public class HttpContext {
    public final EventBuilder eventBuilder;
    public final HttpLog httpLog;

    public HttpContext(EventBuilder eventBuilder, HttpLog httpLog) {
        this.eventBuilder = eventBuilder;
        this.httpLog = httpLog;
    }
}
