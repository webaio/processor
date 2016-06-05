package io.weba.collector.domain.eventsession;

import io.weba.collector.domain.eventsession.exception.AddException;

public interface EventSessionRegistry {
    Integer addEvent(EventSession eventSession) throws AddException;
}
