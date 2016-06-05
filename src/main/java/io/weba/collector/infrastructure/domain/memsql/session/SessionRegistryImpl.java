package io.weba.collector.infrastructure.domain.memsql.session;

import io.weba.eventor.domain.event.VisitorIdentity;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.collector.application.storage.transactional.Client;
import io.weba.collector.application.storage.transactional.exception.ClientException;
import io.weba.collector.domain.session.*;
import io.weba.collector.domain.session.exception.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

public class SessionRegistryImpl implements SessionRegistry {
    private final Client client;
    private final SessionMapper sessionMapper;

    public SessionRegistryImpl(Client client, SessionMapper sessionMapper) {
        this.client = client;
        this.sessionMapper = sessionMapper;
    }

    @Override
    public Integer addNewSession(Session session) throws AddException {
        try {
            String sql = "INSERT INTO session(id, visitorId, startAt, endAt) " +
                    "SELECT ?, ?, ?, " +
                    "CASE " +
                        "WHEN (DATE(NOW()) + INTERVAL 86399 SECOND) >= ADDDATE(?, INTERVAL 30 MINUTE) THEN ADDDATE(?, INTERVAL 30 MINUTE) " +
                        "ELSE (DATE(NOW()) + INTERVAL 86399 SECOND) " +
                    "END";

            return this.client.insert(sql, this.sessionMapper.mapForInsert(session));
        } catch (ClientException e) {
            throw new AddException(e);
        }
    }

    @Override
    public Integer renewActiveSession(Date visitAt, VisitorIdentity visitorId) throws UpdateException {
        try {
            String sql = "UPDATE session SET " +
                    "endAt = " +
                    "CASE " +
                    "WHEN (DATE(NOW()) + INTERVAL 86399 SECOND) >= ADDDATE(?, INTERVAL 30 MINUTE) THEN ADDDATE(?, INTERVAL 30 MINUTE) " +
                    "ELSE (DATE(NOW()) + INTERVAL 86399 SECOND) " +
                    "END " +
                    "WHERE visitorId = ? AND endAt >= ? AND startAt < ?";

            return this.client.update(sql, this.sessionMapper.mapForUpdate(visitAt, visitorId));
        } catch (ClientException e) {
            throw new UpdateException(e);
        }
    }

    @Override
    public Session findActiveSession(Date visitAt, VisitorIdentity visitorId) throws FindException {
        try {
            String sql = "SELECT id, startAt, endAt, visitorId FROM session WHERE visitorId = ? AND endAt >= ? AND startAt < ?";
            Map<Integer, Map<String, String>> select = this.client.select(sql, this.sessionMapper.mapForSelect(visitAt, visitorId));

            if (select.size() == 1) {
                Map<String, String> hashedObject = select.get(0);
                DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

                return new Session(
                        new SessionId(SessionId.fromString(hashedObject.get("id"))),
                        utcFormat.parse(hashedObject.get("startAt")),
                        utcFormat.parse(hashedObject.get("endAt")),
                        new VisitorIdentity(hashedObject.get("visitorId"))
                );
            }

            return null;
        } catch (ClientException | ParseException | EventorException e) {
            throw new FindException(e);
        }
    }
}
