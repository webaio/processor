package io.weba.collector.domain.eventaggregator;

import io.weba.eventor.domain.event.VisitorIdentity;
import io.weba.collector.domain.eventaggregator.exceptions.AddException;
import io.weba.collector.domain.eventaggregator.exceptions.UpdateException;
import io.weba.collector.domain.session.SessionId;

public interface EventAggregatorRegistry {
    Integer add(EventAggregator eventAggregator) throws AddException;
    Integer update(SessionId sessionId, VisitorIdentity visitorId) throws UpdateException;
}
