package io.weba.eventor.infrastructure.event.payload;

import io.weba.eventor.domain.event.IP;
import io.weba.eventor.domain.event.TrackerId;
import io.weba.eventor.domain.event.UserAgent;
import io.weba.eventor.domain.event.payload.Factory;
import io.weba.eventor.domain.event.payload.PageViewPayload;
import io.weba.eventor.domain.event.payload.PageViewPayloadAggregate;
import io.weba.eventor.domain.event.payload.Payload;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.domain.http.Header;
import io.weba.eventor.infrastructure.event.mine.HttpContext;
import io.weba.eventor.infrastructure.event.utils.UrlProvider;
import io.weba.eventor.infrastructure.event.utils.VisitorIdentityProvider;

public class PageViewFactory implements Factory {
    private UrlProvider urlProvider;
    private VisitorIdentityProvider visitorIdentityProvider;

    public PageViewFactory(UrlProvider urlProvider, VisitorIdentityProvider visitorIdentityProvider) {
        this.urlProvider = urlProvider;
        this.visitorIdentityProvider = visitorIdentityProvider;
    }

    public Payload createPayload(HttpContext httpContext) throws EventorException {
        PageViewPayloadAggregate pageViewPayloadAggregate = new PageViewPayloadAggregate(
                urlProvider.provideUrl(httpContext),
                new UserAgent((Header) httpContext.entry.request.headers.bag.get("user-agent")),
                visitorIdentityProvider.provideVisitorIdentity(httpContext),
                new TrackerId(httpContext.entry.parameters.getTrackerId()),
                new IP(httpContext.entry.request.IP)
        );

        return new PageViewPayload(pageViewPayloadAggregate);
    }
}
