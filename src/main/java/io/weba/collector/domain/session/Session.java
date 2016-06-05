package io.weba.collector.domain.session;

import io.weba.eventor.domain.event.VisitorIdentity;

import java.util.Date;

public class Session {
    public final SessionId sessionId;
    public final Date startAt;
    public final VisitorIdentity visitorId;
    public Date endAt;

    public Session(SessionId sessionId, Date startAt, VisitorIdentity visitorId) {
        this.sessionId = sessionId;
        this.startAt = startAt;
        this.visitorId = visitorId;
    }

    public Session(SessionId sessionId, Date startAt, Date endAt, VisitorIdentity visitorId) {
        this.sessionId = sessionId;
        this.startAt = startAt;
        this.visitorId = visitorId;
        this.endAt = endAt;
    }
}
