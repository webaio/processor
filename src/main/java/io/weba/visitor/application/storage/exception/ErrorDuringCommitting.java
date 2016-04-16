package io.weba.visitor.application.storage.exception;

public class ErrorDuringCommitting extends Exception {
    public ErrorDuringCommitting(Exception exception) {
        super("Error during committing object", exception);
    }
}
