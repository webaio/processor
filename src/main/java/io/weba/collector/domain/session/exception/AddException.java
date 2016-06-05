package io.weba.collector.domain.session.exception;

public class AddException extends SessionException {
    public AddException(Exception parent) {
        super("Error during adding new session", parent);
    }
}
