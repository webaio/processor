package io.weba.eventor.domain.event.payload;

import io.weba.eventor.domain.event.*;

public interface Payload {
    URL getUrl();

    UserAgent getUserAgent();

    VisitorIdentity getVisitorIdentity();

    TrackerId getTrackerId();

    IP getIp();
}
