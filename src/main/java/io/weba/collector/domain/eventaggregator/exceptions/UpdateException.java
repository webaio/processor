package io.weba.collector.domain.eventaggregator.exceptions;

public class UpdateException extends EventAggregatorException {
    public UpdateException(Exception parent) {
        super("Error during updating EventAggregator", parent);
    }
}
