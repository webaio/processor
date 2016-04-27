package io.weba.collector.domain.eventsession;

import io.weba.eventor.domain.event.Event;
import io.weba.collector.domain.session.Session;

public class EventSession {
    public final EventSessionId eventSessionId;
    public final Session session;
    public final Event event;

    public EventSession(EventSessionId eventSessionId, Session session, Event event) {
        this.eventSessionId = eventSessionId;
        this.session = session;
        this.event = event;
    }
}
