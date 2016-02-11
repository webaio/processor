package io.weba.eventor.domain.event.payload;

import io.weba.eventor.domain.event.TrackerId;
import io.weba.eventor.domain.event.URL;
import io.weba.eventor.domain.event.UserAgent;
import io.weba.eventor.domain.event.VisitorIdentity;

public class PageViewPayloadAggregate {
    public final URL url;
    public final UserAgent userAgent;
    public final VisitorIdentity visitorIdentity;
    public final TrackerId tracerId;

    public PageViewPayloadAggregate(URL url, UserAgent userAgent, VisitorIdentity visitorIdentity, TrackerId tracerId) {
        this.url = url;
        this.userAgent = userAgent;
        this.visitorIdentity = visitorIdentity;
        this.tracerId = tracerId;
    }
}
