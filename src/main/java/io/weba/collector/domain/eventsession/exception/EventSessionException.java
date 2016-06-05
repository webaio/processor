package io.weba.collector.domain.eventsession.exception;

public class EventSessionException extends Exception {
    public EventSessionException(String message, Exception parent) {
        super(message, parent);
    }
}
