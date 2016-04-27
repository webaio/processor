package io.weba.collector.domain.session.exception;

public class UpdateException extends SessionException {
    public UpdateException(Exception parent) {
        super("Error during adding new session", parent);
    }
}
