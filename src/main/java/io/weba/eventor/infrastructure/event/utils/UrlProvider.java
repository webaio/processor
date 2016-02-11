package io.weba.eventor.infrastructure.event.utils;

import io.weba.eventor.domain.event.URL;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.infrastructure.event.mine.HttpContext;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class UrlProvider {

    public URL provideUrl(HttpContext httpContext) throws EventorException {
        if (isPixelFallbackUrl(httpContext)) {
            return providePixelFallbackUrl(httpContext);
        }

        return provideTrackerUrl(httpContext);
    }

    private URL provideTrackerUrl(HttpContext httpContext) throws EventorException {
        URL url;
        try {
            url = new URL(URLDecoder.decode(httpContext.entry.parameters.getUrl(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new EventorException(e.toString());
        }

        return url;
    }

    private URL providePixelFallbackUrl(HttpContext httpContext) throws EventorException {
        if (httpContext.entry.request.headers.bag.containsKey("referer")) {
            return new URL(httpContext.entry.request.headers.bag.get("referer").value);
        }

        if (httpContext.entry.request.headers.bag.containsKey("referrer")) {
            return new URL(httpContext.entry.request.headers.bag.get("referrer").value);
        }

        throw new EventorException("Cannot utterEventFromAccessLog url from referrer header in pixel fallback strategy.");
    }

    private boolean isPixelFallbackUrl(HttpContext httpContext) {
        return httpContext.entry.parameters.getUrl() == null;
    }
}
