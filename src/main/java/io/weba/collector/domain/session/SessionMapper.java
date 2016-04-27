package io.weba.collector.domain.session;

import io.weba.eventor.domain.event.VisitorIdentity;

import java.util.Date;
import java.util.Map;

public interface SessionMapper {
    Map<Integer, Object> mapForInsert(Session session);
    Map<Integer, Object> mapForUpdate(Date visitAt, VisitorIdentity visitorId);
    Map<Integer, Object> mapForSelect(Date visitAt, VisitorIdentity visitorId);
}
