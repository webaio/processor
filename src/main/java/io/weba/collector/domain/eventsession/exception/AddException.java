package io.weba.collector.domain.eventsession.exception;

public class AddException extends EventSessionException {
    public AddException(Exception parent) {
        super("Error during adding new EventSession", parent);
    }
}
