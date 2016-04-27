package io.weba.collector.infrastructure.domain.memsql.eventaggregator;

import io.weba.eventor.domain.event.VisitorIdentity;
import io.weba.collector.domain.eventaggregator.EventAggregator;
import io.weba.collector.domain.eventaggregator.EventAggregatorMapper;
import io.weba.collector.domain.session.SessionId;

import java.util.HashMap;
import java.util.Map;

public class EventAggregatorMapperImpl implements EventAggregatorMapper {
    @Override
    public Map<Integer, Object> mapForInsert(EventAggregator eventAggregator) {
        HashMap<Integer, Object> map = new HashMap<>();
        map.put(1, eventAggregator.eventAggregatorId.toString());
        map.put(2, eventAggregator.visitId.toString());
        map.put(3, eventAggregator.session.sessionId.toString());

        return map;
    }

    @Override
    public Map<Integer, Object> mapForUpdate(SessionId sessionId, VisitorIdentity visitorId) {
        HashMap<Integer, Object> map = new HashMap<>();
        map.put(1, sessionId.toString());
        map.put(2, visitorId.toString());

        return map;
    }
}
