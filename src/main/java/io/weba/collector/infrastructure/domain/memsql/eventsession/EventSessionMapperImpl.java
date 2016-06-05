package io.weba.collector.infrastructure.domain.memsql.eventsession;

import io.weba.collector.application.serializer.Serializer;
import io.weba.collector.domain.eventsession.EventSession;
import io.weba.collector.domain.eventsession.EventSessionMapper;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class EventSessionMapperImpl implements EventSessionMapper {
    private final Serializer serializer;

    public EventSessionMapperImpl(Serializer serializer) {
        this.serializer = serializer;
    }

    @Override
    public Map<Integer, Object> mapForInsert(EventSession eventSession) {
        HashMap<Integer, Object> map = new HashMap<>();
        map.put(1, eventSession.eventSessionId.toString());
        map.put(2, eventSession.event.id.toString());
        map.put(3, eventSession.session.sessionId.toString());
        map.put(4, eventSession.event.payload.getVisitorIdentity().toString());
        map.put(5, new Timestamp(eventSession.event.dates.browserDate.getTime()));
        map.put(6, eventSession.event.type.toString());
        map.put(7, this.serializer.serialize(eventSession.event.payload));
        map.put(8, this.serializer.serialize(eventSession.event.device));
        map.put(9, this.serializer.serialize(eventSession.event.localization));
        map.put(10, this.serializer.serialize(eventSession.event.dates));

        return map;
    }
}
