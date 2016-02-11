package io.weba.eventor.domain.event.payload;

import io.weba.eventor.domain.event.TrackerId;
import io.weba.eventor.domain.event.URL;
import io.weba.eventor.domain.event.UserAgent;
import io.weba.eventor.domain.event.VisitorIdentity;

public interface Payload {
    URL getUrl();

    UserAgent getUserAgent();

    VisitorIdentity getVisitorIdentity();

    TrackerId getTrackerId();
}
