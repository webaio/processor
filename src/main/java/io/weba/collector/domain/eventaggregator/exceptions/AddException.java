package io.weba.collector.domain.eventaggregator.exceptions;

public class AddException extends EventAggregatorException {
    public AddException(Exception parent) {
        super("Error during adding new EventAggregator", parent);
    }
}
