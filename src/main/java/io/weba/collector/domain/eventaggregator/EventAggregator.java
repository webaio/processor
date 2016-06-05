package io.weba.collector.domain.eventaggregator;

import io.weba.eventor.domain.event.VisitorIdentity;
import io.weba.collector.domain.session.Session;

public class EventAggregator {
    public final EventAggregatorId eventAggregatorId;
    public final Session session;
    public final VisitorIdentity visitId;

    public EventAggregator(EventAggregatorId eventAggregatorId, Session session, VisitorIdentity visitId) {
        this.eventAggregatorId = eventAggregatorId;
        this.session = session;
        this.visitId = visitId;
    }
}
