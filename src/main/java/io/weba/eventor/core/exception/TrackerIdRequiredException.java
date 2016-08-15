package io.weba.eventor.core.exception;

public class TrackerIdRequiredException extends DropEventException {
    public TrackerIdRequiredException(String message) {
        super(message);
    }

    public TrackerIdRequiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
