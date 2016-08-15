/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.eventor.accesslog.matcher;

import io.weba.eventor.accesslog.AccessLog;
import io.weba.eventor.accesslog.parameters.Input;
import io.weba.eventor.accesslog.parameters.Output;
import io.weba.eventor.core.event.Payload;
import io.weba.eventor.core.exception.DropEventException;
import io.weba.eventor.core.exception.EventorException;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

public class UrlMatcherImpl implements Matcher {
    @Override
    public void match(AccessLog accessLog) throws EventorException {
        URL url = provideUrl(accessLog);
        Payload payload = accessLog.builder.payload;

        payload.put(Output.URL_FULL.name, url.toExternalForm());
        payload.put(Output.URL_PARTS.name, new URLParts(
                url.getProtocol(),
                url.getHost(),
                url.getQuery(),
                url.getAuthority(),
                url.getPath(),
                url.getUserInfo()
        ));
    }

    private URL provideUrl(AccessLog accessLog) throws EventorException {
        if (isPixelFallbackUrl(accessLog)) {
            return providePixelFallbackUri(accessLog);
        }

        return provideTrackerUrl(accessLog);
    }

    private URL provideTrackerUrl(AccessLog accessLog) throws EventorException {
        URL url;
        try {
            url = new URL(URLDecoder.decode((String) accessLog.parameters.get(Input.URL.name), "UTF-8"));
        } catch (UnsupportedEncodingException | MalformedURLException e) {
            throw new DropEventException(e.getMessage(), e);
        }

        return url;
    }

    private URL providePixelFallbackUri(AccessLog accessLog) throws EventorException {
        String URLString = null;
        if (accessLog.requestHeaders.containsKey("referer")) {
            URLString = accessLog.requestHeaders.get("referer");
        }

        if (accessLog.requestHeaders.containsKey("referrer")) {
            URLString = accessLog.requestHeaders.get("referrer");
        }

        try {
            return new URL(URLString);
        } catch (MalformedURLException e) {
            throw new EventorException("Cannot obtain url from referrer header in pixel fallback strategy.", e);
        }
    }

    private boolean isPixelFallbackUrl(AccessLog accessLog) {
        return accessLog.parameters.get(Input.URL.name) == null;
    }

    private final class URLParts {
        public final String protocol;
        public final String host;
        public final String query;
        public final String authority;
        public final String path;
        public final String userInfo;

        public URLParts(String protocol, String host, String query, String authority, String path, String userInfo) {
            this.protocol = protocol;
            this.host = host;
            this.query = query;
            this.authority = authority;
            this.path = path;
            this.userInfo = userInfo;
        }
    }
}
