package io.weba.collector.domain.eventaggregator.exceptions;

public class EventAggregatorException extends Exception {
    public EventAggregatorException(String message, Exception parent) {
        super(message, parent);
    }
}
