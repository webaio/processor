package io.weba.eventor.infrastructure.event.utils;

import io.weba.eventor.domain.event.VisitorIdentity;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.domain.http.Header;
import io.weba.eventor.infrastructure.event.mine.HttpContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VisitorIdentityProvider {

    public VisitorIdentity provideVisitorIdentity(HttpContext httpContext) throws EventorException {
        if (!httpContext.entry.response.headers.bag.containsKey("set-cookie")) {
            throw new EventorException("Response header should contains set-cookie.");
        }

        Header setCookie = httpContext.entry.response.headers.bag.get("set-cookie");
        Pattern cookiePattern = Pattern.compile(VisitorIdentity.IDENTITY_PATTERN);
        Matcher cookieMatcher = cookiePattern.matcher(setCookie.toString().toLowerCase());

        if (!cookieMatcher.find()) {
            throw new EventorException("Response header set-cookie should contains visitor identity.");
        }

        return new VisitorIdentity(cookieMatcher.group(1));
    }
}
