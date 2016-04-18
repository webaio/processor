package io.weba.visitor.application.storage.exception;

public class ErrorDuringInsertingException extends Exception {
    public ErrorDuringInsertingException(Exception previous) {
        super("Error during inserting/updating record to elasticsearch", previous);
    }
}
