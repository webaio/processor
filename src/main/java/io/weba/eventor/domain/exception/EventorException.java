package io.weba.eventor.domain.exception;

public class EventorException extends Exception {
    public EventorException(String message) {
        super(message);
    }

    public EventorException(String message, Throwable cause) {
        super(message, cause);
    }
}
