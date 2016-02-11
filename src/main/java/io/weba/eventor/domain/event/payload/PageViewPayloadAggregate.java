package io.weba.eventor.domain.event.payload;

import io.weba.eventor.domain.event.*;

public class PageViewPayloadAggregate {
    public final URL url;
    public final UserAgent userAgent;
    public final VisitorIdentity visitorIdentity;
    public final TrackerId tracerId;
    public final IP ip;

    public PageViewPayloadAggregate(URL url, UserAgent userAgent, VisitorIdentity visitorIdentity, TrackerId tracerId, IP ip) {
        this.url = url;
        this.userAgent = userAgent;
        this.visitorIdentity = visitorIdentity;
        this.tracerId = tracerId;
        this.ip = ip;
    }
}
