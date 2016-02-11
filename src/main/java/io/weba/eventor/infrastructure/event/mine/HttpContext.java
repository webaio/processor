package io.weba.eventor.infrastructure.event.mine;

import io.weba.eventor.domain.event.EventBuilder;
import io.weba.eventor.domain.log.Entry;

public class HttpContext {
    public final EventBuilder eventBuilder;
    public final Entry entry;

    public HttpContext(EventBuilder eventBuilder, Entry entry) {
        this.eventBuilder = eventBuilder;
        this.entry = entry;
    }
}
