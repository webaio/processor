/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.eventor.accesslog;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import io.weba.eventor.accesslog.matcher.Matcher;
import io.weba.eventor.accesslog.matcher.MatcherFactory;
import io.weba.eventor.core.event.Event;
import io.weba.eventor.core.event.EventBuilder;
import io.weba.eventor.core.eventor.Eventor;
import io.weba.eventor.core.exception.DropEventException;
import io.weba.eventor.core.exception.EventorException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class AccessLogEventorImpl implements Eventor {
    private final Gson gson;
    private final Matcher matcher;
    private final Type type = new TypeToken<Map<String, Object>>() {}.getType();
    private final EventBuilder eventBuilder;

    public AccessLogEventorImpl(Matcher matcher, Gson gson, EventBuilder eventBuilder) {
        this.matcher = matcher;
        this.gson = gson;
        this.eventBuilder = eventBuilder;
    }

    public AccessLogEventorImpl(Matcher matcher) {
        this(matcher, new Gson(), new EventBuilder());
    }

    public static AccessLogEventorImpl newInstance() throws IOException {
        return new AccessLogEventorImpl(
                new MatcherFactory().create(),
                new Gson(),
                new EventBuilder()
        );
    }

    @Override
    public Event exploitEvent(String rawEvent) throws EventorException {
        eventBuilder.clean();

        try {
            matcher.match(new AccessLog(gson.fromJson(rawEvent, type), eventBuilder));

            return eventBuilder.build();
        } catch (Exception exception) {
            throw new DropEventException(exception.getMessage(), exception);
        }
    }
}
