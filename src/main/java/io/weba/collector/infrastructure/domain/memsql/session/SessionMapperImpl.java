package io.weba.collector.infrastructure.domain.memsql.session;

import io.weba.collector.domain.session.SessionMapper;
import io.weba.eventor.domain.event.VisitorIdentity;
import io.weba.collector.domain.session.Session;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SessionMapperImpl implements SessionMapper {
    @Override
    public Map<Integer, Object> mapForInsert(Session session) {
        HashMap<Integer, Object> map = new HashMap<>();
        map.put(1, session.sessionId.toString());
        map.put(2, session.visitorId.toString());
        map.put(3, new Timestamp(session.startAt.getTime()));
        map.put(4, new Timestamp(session.startAt.getTime()));
        map.put(5, new Timestamp(session.startAt.getTime()));

        return map;
    }

    @Override
    public Map<Integer, Object> mapForUpdate(Date visitAt, VisitorIdentity visitorId) {
        HashMap<Integer, Object> map = new HashMap<>();
        map.put(1, new Timestamp(visitAt.getTime()));
        map.put(2, new Timestamp(visitAt.getTime()));
        map.put(3, visitorId.toString());
        map.put(4, new Timestamp(visitAt.getTime()));
        map.put(5, new Timestamp(visitAt.getTime()));

        return map;
    }

    @Override
    public Map<Integer, Object> mapForSelect(Date visitAt, VisitorIdentity visitorId) {
        HashMap<Integer, Object> map = new HashMap<>();
        map.put(1, visitorId.toString());
        map.put(2, new Timestamp(visitAt.getTime()));
        map.put(3, new Timestamp(visitAt.getTime()));

        return map;
    }
}
