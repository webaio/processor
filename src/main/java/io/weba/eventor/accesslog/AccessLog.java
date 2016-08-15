/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.eventor.accesslog;

import io.weba.eventor.core.event.EventBuilder;
import io.weba.eventor.core.exception.DropEventException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class AccessLog {
    public final Map<String, Object> log;
    public final EventBuilder builder;
    public final Map parameters;
    public final HashMap<String, String> requestHeaders;
    public final HashMap<String, String> responseHeaders;

    public AccessLog(Map<String, Object> log, EventBuilder builder) throws DropEventException {
        this.log = log;
        this.builder = builder;
        this.parameters = prepareParameters();
        this.requestHeaders = prepareHeaders("request.headers");
        this.responseHeaders = prepareHeaders("response.headers");
    }

    public Object readAtPath(String path) throws DropEventException {
        Object result = log;
        for (String pathPart : path.split(Pattern.quote("."))) {
            if (!(result instanceof Map)) {
                throw new DropEventException(String.format("Cannot find %s in map.", path));
            }

            Map<String, Object> map = (Map<String, Object>) result;
            result = map.get(pathPart);

            Objects.requireNonNull(result);
        }

        return result;
    }

    protected Map<String, String> prepareParameters() throws DropEventException {
        URI uri = URI.create((String) readAtPath("request.uri"));

        return UriComponentsBuilder
                .fromUri(uri)
                .build()
                .getQueryParams()
                .toSingleValueMap();
    }

    protected HashMap<String, String> prepareHeaders(String destination) throws DropEventException {
        HashMap<String, String> headersMap = new HashMap<String, String>();

        byte[] headersBytes = Base64.getDecoder().decode((String) readAtPath(destination));
        for (String headerName : new String(headersBytes).split("\\r?\\n")) {
            Integer indexOf = headerName.indexOf(":");
            if (indexOf.equals(-1)) {
                continue;
            }

            headersMap.put(
                    headerName.substring(0, indexOf).trim().toLowerCase(),
                    headerName.substring(indexOf + 1, headerName.length())
            );

        }
        return headersMap;
    }
}
