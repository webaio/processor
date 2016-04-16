package io.weba.visitor.domain.visit.visitor;

import java.util.Date;

final public class Visitor {
    public final VisitorId visitorId;
    public final Date firstVisitAt;
    public final Integer numberOfActions;
    public final Date lastVisitAt;
    public final Date serverFirstVisitAt;
    public final Date serverLastVisitAt;
    public final Date sessionEndedAt;

    private Visitor(VisitorId id, Integer numberOfActions, Date firstVisitAt, Date lastVisitAt, Date serverFirstVisitAt, Date serverLastVisitAt, Date sessionEndedAt) {
        this.visitorId = id;
        this.numberOfActions = numberOfActions;
        this.firstVisitAt = firstVisitAt;
        this.lastVisitAt = lastVisitAt;
        this.serverFirstVisitAt = serverFirstVisitAt;
        this.serverLastVisitAt = serverLastVisitAt;
        this.sessionEndedAt = sessionEndedAt;
    }

    public static Visitor createNewVisitorForVisit(VisitorId id, Date firstVisitAt, Date serverFirstVisitAt) {
        return new Visitor(id, 1, firstVisitAt, firstVisitAt, serverFirstVisitAt, serverFirstVisitAt, new Date(serverFirstVisitAt.getTime() + 1000 * 60 * 30));
    }

    public static Visitor loadFromStorage(VisitorId id, Integer numberOfActions, Date firstVisitAt, Date lastVisitAt, Date serverFirstVisitAt, Date serverLastVisitAt, Date sessionEndedAt) {
        return new Visitor(id, numberOfActions, firstVisitAt, lastVisitAt, serverFirstVisitAt, serverLastVisitAt, sessionEndedAt);
    }
}
