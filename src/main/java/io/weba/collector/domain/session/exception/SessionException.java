package io.weba.collector.domain.session.exception;

public class SessionException extends Exception {
    public SessionException(String message, Exception parent) {
        super(message, parent);
    }
}
