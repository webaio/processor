package io.weba.eventor.domain.event.payload;

import io.weba.eventor.domain.event.TrackerId;
import io.weba.eventor.domain.event.URL;
import io.weba.eventor.domain.event.UserAgent;
import io.weba.eventor.domain.event.VisitorIdentity;

public class PageViewPayload implements Payload {

    private final PageViewPayloadAggregate pageViewPayloadAggregate;

    public PageViewPayload(PageViewPayloadAggregate pageViewPayloadAggregate) {
        this.pageViewPayloadAggregate = pageViewPayloadAggregate;
    }

    public URL getUrl() {
        return this.pageViewPayloadAggregate.url;
    }

    public UserAgent getUserAgent() {
        return this.pageViewPayloadAggregate.userAgent;
    }

    public VisitorIdentity getVisitorIdentity() {
        return this.pageViewPayloadAggregate.visitorIdentity;
    }

    public TrackerId getTrackerId() {
        return this.pageViewPayloadAggregate.tracerId;
    }
}
