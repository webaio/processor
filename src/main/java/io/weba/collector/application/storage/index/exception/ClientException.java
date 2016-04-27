package io.weba.collector.application.storage.index.exception;

public class ClientException extends Exception  {
    public ClientException(Exception previous) {
        super("Elasticsearch client exception", previous);
    }
}
