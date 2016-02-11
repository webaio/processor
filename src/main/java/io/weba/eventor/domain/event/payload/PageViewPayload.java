package io.weba.eventor.domain.event.payload;

import io.weba.eventor.domain.event.*;

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

    public IP getIp() {
        return this.pageViewPayloadAggregate.ip;
    }
}
