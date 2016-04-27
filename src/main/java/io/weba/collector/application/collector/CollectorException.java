package io.weba.collector.application.collector;

public class CollectorException extends Exception {
    public CollectorException(Exception parent) {
        super("Error during collect event, session or aggregator", parent);
    }
}
