package io.weba.collector.domain.session.exception;

public class FindException extends SessionException {
    public FindException(Exception parent) {
        super("Error during finding session", parent);
    }
}
