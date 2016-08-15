package io.weba.eventor.accesslog.matcher;

import io.weba.eventor.accesslog.AccessLog;
import io.weba.eventor.accesslog.parameters.Input;
import io.weba.eventor.accesslog.parameters.Output;
import io.weba.eventor.core.exception.EventorException;
import io.weba.eventor.core.exception.TrackerIdRequiredException;

import java.util.UUID;

public class TrackerIdMatcherImpl implements Matcher {
    @Override
    public void match(AccessLog accessLog) throws EventorException {
        if (!accessLog.parameters.containsKey(Input.TRACKER_ID.name)) {
            throw new TrackerIdRequiredException("Tracker ID is required in rich event and in pixel fallback.");
        }

        accessLog.builder.payload.put(
                Output.TRACKER_ID.name,
                UUID.fromString((String)accessLog.parameters.get(Input.TRACKER_ID.name)).toString()
        );
    }
}
