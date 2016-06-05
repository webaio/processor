package io.weba.collector.application.storage.transactional.exception;

public class ClientException extends Exception {
    public ClientException(Exception previous) {
        super("Transactional client exception", previous);
    }
}
