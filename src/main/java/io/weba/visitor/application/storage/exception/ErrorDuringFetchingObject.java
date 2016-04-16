package io.weba.visitor.application.storage.exception;

public class ErrorDuringFetchingObject extends Exception {
    public ErrorDuringFetchingObject(Exception exception) {
        super("Error during fetching object", exception);
    }
}
