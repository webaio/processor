package io.weba.collector.application.collector;

import io.weba.eventor.domain.event.Event;

public interface Collector {
    void collect(Event event) throws CollectorException;
}
