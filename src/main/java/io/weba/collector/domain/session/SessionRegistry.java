package io.weba.collector.domain.session;

import io.weba.eventor.domain.event.VisitorIdentity;
import io.weba.collector.domain.session.exception.AddException;
import io.weba.collector.domain.session.exception.FindException;
import io.weba.collector.domain.session.exception.UpdateException;

import java.util.Date;

public interface SessionRegistry {
    Session findActiveSession(Date visitAt, VisitorIdentity visitorId) throws FindException;
    Integer addNewSession(Session session) throws AddException;
    Integer renewActiveSession(Date visitAt, VisitorIdentity visitorId) throws UpdateException;
}
