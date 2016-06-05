package io.weba.collector.domain.eventaggregator;

import io.weba.eventor.domain.event.VisitorIdentity;
import io.weba.collector.domain.session.SessionId;

import java.util.Map;

public interface EventAggregatorMapper {
    Map<Integer, Object> mapForInsert(EventAggregator eventAggregator);
    Map<Integer, Object> mapForUpdate(SessionId sessionId, VisitorIdentity visitorId);
}
